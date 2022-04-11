package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class KafkaUtils {
    private static Properties propertiesProducer;
    private static Properties propertiesConsumer;

    private static KafkaProducer<String, String> kafkaProducer;
    private static KafkaConsumer<String, String> kafkaConsumer;

    static {
        propertiesProducer = new Properties();
        propertiesProducer.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.100.26.201:9092");
        propertiesProducer.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        propertiesProducer.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        propertiesConsumer = new Properties();
        //自动提交延时
        propertiesConsumer.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //开启自动提交
        propertiesConsumer.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        propertiesConsumer.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        propertiesConsumer.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        propertiesConsumer.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "47.100.26.201:9092");
        propertiesConsumer.put(ConsumerConfig.GROUP_ID_CONFIG,"bigdata");
        // 消费者重置offset
        propertiesConsumer.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        kafkaProducer = new KafkaProducer(propertiesProducer);
        kafkaConsumer = new KafkaConsumer(propertiesConsumer);
    }


    public static KafkaProducer getProducer() {
        return kafkaProducer;
    }

    public static KafkaConsumer getConsumer() {
        return kafkaConsumer;
    }

}
