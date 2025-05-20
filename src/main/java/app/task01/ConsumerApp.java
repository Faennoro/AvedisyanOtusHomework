package app.task01;

import app.Util.ConsumerConfiguration;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;

import java.time.Duration;
import java.util.Arrays;

public class ConsumerApp {

    public static void main(String[] args) {
        System.out.printf("Запущено приложение ConsumerApp.%n");
        String topic1 = "topic1";
        String topic2 = "topic2";

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(ConsumerConfiguration.getConfig(c -> {
            c.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        }));
        try (consumer) {
            consumer.subscribe(Arrays.asList(topic1, topic2));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Topic %s -> Key %s : Value %s%n", record.topic(), record.key(), record.value());
                }
            }
        } catch (KafkaException e) {
            System.out.println(e.getMessage());
        }

    }
}
