package cn.xutingyin.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

@Configuration
// @EnableSolrRepositories
public class SolrConfig {

    @Value("${spring.data.solr.host}")
    private String solrURL;
    @Value("${solr.core-name}")
    private String coreName;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrURL + coreName).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}
