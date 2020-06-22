package cn.xutingyin.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
    @Value("${elasticsearch.master.host}")
    private String masterHost;
    @Value("${elasticsearch.master.port}")
    private int masterPort;
    @Value("${elasticsearch.slave1.host}")
    private String slave1Host;
    @Value("${elasticsearch.slave1.port}")
    private int slave1Port;
    @Value("${elasticsearch.slave2.host}")
    private String slave2Host;
    @Value("${elasticsearch.slave2.port}")
    private int slave2Port;

    @Value("${elasticsearch.scheme}")
    private String scheme;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client =
            new RestHighLevelClient(RestClient.builder(new HttpHost(masterHost, masterPort, scheme),
                new HttpHost(slave1Host, slave1Port, scheme), new HttpHost(slave2Host, slave2Port, scheme)));
        return client;
    }
}
