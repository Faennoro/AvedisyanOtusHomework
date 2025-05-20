package app.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ConsumerConfiguration extends Configuration {

    public static Map<String, Object> getConfig(Consumer<Map<String, Object>> builder) {
        var map = new HashMap<>(consumerConfig);
        builder.accept(map);
        return map;
    }
}
