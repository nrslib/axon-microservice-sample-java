package com.example.order.service.config;

import com.thoughtworks.xstream.XStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfiguration {
    @Bean
    public XStream xStream() {
        var xStream = new XStream();
        xStream.allowTypesByWildcard(
                new String[]{"com.example.**", "org.apache.kafka.*"}
        );

        return xStream;
    }
}
