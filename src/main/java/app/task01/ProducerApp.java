package app.task01;

import app.Util.ProducerConfiguration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProducerApp {

    private static final String TOPIC_1_NAME = "topic1";
    private static final String TOPIC_2_NAME = "topic2";
    private static final String TOPIC_MESSAGE = "testMessage";
    private static List<String> topics;
    private static final KafkaProducer<String,String> producer = new KafkaProducer<>(ProducerConfiguration.getConfig(b -> b.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "kafkaApp")));;

    public static void main(String[] args) {
        System.out.printf("Запущено приложение ProducerApp.%n");
        topics = Arrays.asList(TOPIC_1_NAME, TOPIC_2_NAME);

        producer.initTransactions();

        sendTransaction(false, 5);
        sendTransaction(true, 2);

    }

    public static void sendTransaction(boolean abort, int count) {
        try {
            producer.beginTransaction();
            for (int i = 1; i <= count; i++) {
                for (String topic : topics) {
                    producer.send(new ProducerRecord<>(topic, i+"", TOPIC_MESSAGE+i));
                }

            }
            if (abort) {
                producer.flush();
                TimeUnit.SECONDS.sleep(5);
                producer.abortTransaction();
                System.out.printf("Отменена транзакция к топикам %s в количестве %d сообщений.%n", topics.toString(), count);
            } else {
                producer.commitTransaction();
                producer.flush();
                System.out.printf("Отправлена транзакция к топикам %s в количестве %d сообщений.%n", topics.toString(), count);
            }

        }
        catch (KafkaException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
