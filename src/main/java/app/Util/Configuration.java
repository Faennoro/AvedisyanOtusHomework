package app.Util;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;

abstract class Configuration {

    public static final String HOST = "localhost:29092";

    public static final Map<String, Object> producerConfig = Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST,
            ProducerConfig.CLIENT_ID_CONFIG, "kafkaApp",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    public static final Map<String, Object> consumerConfig = Map.of(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST,
            ConsumerConfig.GROUP_ID_CONFIG, "kafkaApp",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

}
