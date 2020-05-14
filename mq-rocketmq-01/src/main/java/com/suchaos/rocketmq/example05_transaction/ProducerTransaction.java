package com.suchaos.rocketmq.example05_transaction;

import com.suchaos.rocketmq.util.JsonUtil;
import com.suchaos.rocketmq.util.SleepUtil;
import com.suchaos.rocketmq.util.SuConstants;
import com.suchaos.rocketmq.util.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 事务消息
 *
 * @author suchao
 * @date 2020/5/13
 */
@Slf4j
public class ProducerTransaction {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        TransactionMQProducer producer = new TransactionMQProducer(SuConstants.PRODUCERGROUP01);
        // 设置 nameserver
        producer.setNamesrvAddr(SuConstants.NAMESERVER);

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                log.info("execute start...");
                // 执行本地事务，如果成功返回 commit
                log.info("arg: " + arg);
                log.info("msg: " + JsonUtil.byteToUser(msg.getBody()));
                log.info(msg.toString());
                // 模拟本地任务
                SleepUtil.sleepSecond(1);
                // 模拟无法返回 commit / rollback
                try {
                    // do something
                } catch (Exception e) {
                    //return LocalTransactionState.UNKNOW;
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }

                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                log.info("check start...");
                // 查询一些状态，比如数据库中存上了，等等
                // 测试是：默认一分钟执行一次？
                log.info("msg: " + JsonUtil.byteToUser(msg.getBody()));
                log.info(msg.toString());
                return LocalTransactionState.UNKNOW;
                //return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        producer.start();

        Message message = new Message(SuConstants.TOPIC01, JsonUtil.userToByte(User.defaultUser()));
        // Send message in synchronous mode
        TransactionSendResult transactionSendResult = producer.sendMessageInTransaction(message, "传入的参数");
        log.info(transactionSendResult.toString());
        //producer.shutdown();
    }
}
