package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class producer1 {
    public static void main(String[] args) {
//        System.out.println("Hello World ! ");
//        1. create producer properties
        String bootstrapServers = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
//        2. create producer
        KafkaProducer<String,String> first_producer = new KafkaProducer<String, String>(properties);
//        3. create producer record
        ProducerRecord<String,String> record = new ProducerRecord<>("my_first","Hye Quazi ,The test was successful");
//        4. send the data
        System.out.println("Before sending the data: ");
        first_producer.send(record); // for sending the record
        System.out.println("After sending the data: ");
        first_producer.flush(); // for flush
        System.out.println("After flushing the data: ");
        first_producer.close(); // for closing
        System.out.println("After closing the producer: ");
    }

}
