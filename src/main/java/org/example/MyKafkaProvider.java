package org.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

public class MyKafkaProvider {

    public static void main(String[] args) {

        KafkaProducer kafkaProducer = KafkaUtils.getProducer();

        //1.普通发送
        ProducerRecord producerRecord = new ProducerRecord("mykafka", "my-1", "普通发送");
        kafkaProducer.send(producerRecord);


        ProducerRecord producerRecord1 = new ProducerRecord("mykafka", "回调函数");
        //2.回调函数
        kafkaProducer.send(producerRecord1, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println(recordMetadata.partition() + "-" + recordMetadata.offset());
                }
            }
        });

        //3.分区
        ProducerRecord producerRecord2 = new ProducerRecord("mykafka", 0,"my-1", "分区");
        kafkaProducer.send(producerRecord2);


        //4.同步发送
        try {
            kafkaProducer.send(producerRecord).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        kafkaProducer.close();
    }
}
