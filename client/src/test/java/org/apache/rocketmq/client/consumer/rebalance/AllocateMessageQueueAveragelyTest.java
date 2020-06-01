package org.apache.rocketmq.client.consumer.rebalance;

import java.util.ArrayList;
import java.util.List;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;

public class AllocateMessageQueueAveragelyTest {

    @Test
    public  void test() {
        List<MessageQueue> mqAll = new ArrayList<MessageQueue>();
        List<String> cidAll = new ArrayList<String>();
        for (int i = 0; i < 10; i++){
            MessageQueue messageQueue = new MessageQueue();
            messageQueue.setQueueId(i);
            mqAll.add(messageQueue);
        }
        for (int i = 0 ; i < 3 ;i++){
            cidAll.add(String.valueOf(i));
        }
        System.out.println("maAll:"+mqAll);
        String currentCID = "2";

        List<MessageQueue> result = new ArrayList<MessageQueue>();

        int index = cidAll.indexOf(currentCID);
        int mod = mqAll.size() % cidAll.size();
        int averageSize =
                mqAll.size() <= cidAll.size() ? 1 : (mod > 0 && index < mod ? mqAll.size() / cidAll.size()
                        + 1 : mqAll.size() / cidAll.size());
        int startIndex = (mod > 0 && index < mod) ? index * averageSize : index * averageSize + mod;
        int range = Math.min(averageSize, mqAll.size() - startIndex);
        for (int i = 0; i < range; i++) {
            result.add(mqAll.get((startIndex + i) % mqAll.size()));
        }
        System.out.println(result);
    }
}
