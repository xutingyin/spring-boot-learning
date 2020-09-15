package cn.xutingyin.solr;

import java.io.IOException;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrApiTest {
    @Value("${spring.data.solr.host}")
    private String solrUrl;
    String coreName = "book_core";
    SolrClient solrClient = null;

    @Autowired
    private SolrTemplate solrTemplate;

    @Before
    public void createServer() {
        solrClient = new HttpSolrClient.Builder(solrUrl + coreName).withConnectionTimeout(10000)
            .withSocketTimeout(60000).build();
        System.out.println(solrClient + " 客户端创建成功");
    }

    @After
    public void releaseConnection() throws IOException, SolrServerException {
        if (null != solrClient) {
            solrClient.commit();
            solrClient.close();
        }
    }

    @Test
    public void addDocument() throws IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        // id 域必须要有，是进行schema.xml 中作为主键进行区分不同记录的标识
        document.addField("id", UUID.randomUUID().toString());
        document.addField("name", "周星星".toString());
        document.addField("age", "25");
        document.addField("address", "广东省-深圳市-宝安区-西乡-桃源居");
        solrClient.add(document);
    }
}