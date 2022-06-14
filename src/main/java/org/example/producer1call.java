package org.example;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class producer1call {
    public static void main(String[] args) {
// Producer without keys
//        1. create producer properties
        String bootstrapServers = "127.0.0.1:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
//        2. create producer
        KafkaProducer<String,String> first_producer = new KafkaProducer<String, String>(properties);
//        3. create producer record
        ProducerRecord<String,String> record = new ProducerRecord<>("my_first","Hye Kafka");
//        4. send the data
        first_producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                Logger logger= LoggerFactory.getLogger(producer1call.class);
                if (e== null) {
                    logger.info("Successfully received the details as: \n" +
                            "Topic:" + recordMetadata.topic() + "\n" +
                            "Partition:" + recordMetadata.partition() + "\n" +
                            "Offset" + recordMetadata.offset() + "\n" +
                            "Timestamp" + recordMetadata.timestamp());
                }

                else {
                    logger.error("Can't produce,getting error",e);

                }
            }
        });
    }
}
