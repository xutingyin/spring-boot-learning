package cn.xutingyin.elasticsearch.client;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;

public class EsClient {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client =
            new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 1、创建索引名
        CreateIndexRequest request = new CreateIndexRequest("sunshine");
        // 索引settings配置
        request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));

        // 3.设置索引的mapping
        // request.mapping(
        // " {\n" + "\"_doc\": {\n" + " \"properties\": {\n" + " \"message\": {\n"
        // + " \"type\": \"text\"\n" + " }\n" + " }\n" + " }\n" + " }",
        // XContentType.JSON);

        request.settings();

        // 设置索引别名
        request.alias(new Alias("lab1"));

        // 5.发送请求
        // 5.1同步方式
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        // 处理响应
        boolean acknowledged = response.isAcknowledged();
        boolean shardsAcknowledged = response.isShardsAcknowledged();

        System.out.println("请求结果---------------");
        System.out.println("acknowledged:" + acknowledged);
        System.out.println("shardsAcknowledged:" + shardsAcknowledged);
        client.close();
    }
}
