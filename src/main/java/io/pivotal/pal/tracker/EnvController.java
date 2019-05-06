package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String port;
    private String memLimit;
    private String cfIndex;
    private String cfAddress;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memLimit,
                         @Value("${cf.instance.index:NOT SET}") String cfIndex,
                         @Value("${cf.instance.addr:NOT SET}") String cfAddress) {
        this.port = port;
        this.memLimit = memLimit;
        this.cfIndex = cfIndex;
        this.cfAddress = cfAddress;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> envMap = new HashMap<>();
        envMap.put("PORT", this.port);
        envMap.put("MEMORY_LIMIT", this.memLimit);
        envMap.put("CF_INSTANCE_INDEX", this.cfIndex);
        envMap.put("CF_INSTANCE_ADDR", this.cfAddress);

        return envMap;
    }
}
