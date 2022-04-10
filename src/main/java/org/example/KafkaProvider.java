package org.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class KafkaProvider {

    public static void main(String[] args) {


        Properties properties = new Properties();
        properties.put("bootstrap.servers", "47.100.26.201:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> kafkaProducer = new KafkaProducer(properties);
        ProducerRecord producerRecord = new ProducerRecord("mykafka", "my-1", "1");

        ProducerRecord producerRecord1 = new ProducerRecord("mykafka","my-1");

        ///
        kafkaProducer.send(producerRecord);

        kafkaProducer.send(producerRecord1 ,new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println(recordMetadata.partition() + "-" + recordMetadata.offset());
                }
            }
        });


        kafkaProducer.close();
    }
}
