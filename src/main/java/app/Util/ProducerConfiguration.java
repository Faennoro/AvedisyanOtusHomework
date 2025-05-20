package app.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ProducerConfiguration extends Configuration {

    public static Map<String, Object> getConfig(Consumer<Map<String, Object>> builder) {
        var map = new HashMap<>(producerConfig);
        builder.accept(map);
        return map;
    }
}
