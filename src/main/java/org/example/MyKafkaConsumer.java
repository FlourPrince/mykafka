package org.example;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Map;

public class MyKafkaConsumer {

    public static void main(String[] args) {
        KafkaConsumer kafkaConsumer = KafkaUtils.getConsumer();

        //订阅主题
        kafkaConsumer.subscribe(Arrays.asList("mykafka"));

        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);

            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key() + "--" + consumerRecord.value());
            }

            //同步提交
            kafkaConsumer.commitAsync();

            //异步提交
            kafkaConsumer.commitAsync(new OffsetCommitCallback() {
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {

                }
            });
        }
    }
}
