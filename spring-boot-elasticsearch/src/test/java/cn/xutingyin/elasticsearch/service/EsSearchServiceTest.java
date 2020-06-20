package cn.xutingyin.elasticsearch.service;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import cn.xutingyin.elasticsearch.entity.User;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class EsSearchServiceTest {
    @Autowired
    private EsSearchService esSearchService;

    @Autowired
    private ContentService contentService;

    private String index = "sunshine";

    @Test
    void createIndex() throws IOException {
        System.out.println(esSearchService.CreateIndex("sunshine"));
    }

    @Test
    void isExit() throws IOException {

        System.out.println(esSearchService.isExit(index));
    }

    @Test
    void delete() throws IOException {
        System.out.println(esSearchService.delete(index));
    }

    @Test
    void addDocument() throws IOException {
        JSONObject object = new JSONObject();
        object.put("name", "susan");
        object.put("age", 22);
        object.put("sex", "female");
        System.out.println(esSearchService.addDocument(index, "1", object));
    }

    @Test
    void isExistDocument() throws IOException {
        System.out.println(esSearchService.isExistDocument(index, "1"));
    }

    @Test
    void getDocument() throws IOException {
        System.out.println(esSearchService.GetDocument(index, "1"));
    }

    @Test
    void updateDocument() throws IOException {
        User user = new User("xuty", 26);
        System.out.println(esSearchService.UpdateDocument(user, index, "1"));
    }

    @Test
    void deleteDocument() throws IOException {
        System.out.println(esSearchService.DeleteDocument(index, "1"));
    }

    @Test
    void bulkAddDocument() throws IOException {
        /*  List<User> users = new ArrayList<>();
        users.add(new User("zhangsan", 1));
        users.add(new User("lishi", 12));
        users.add(new User("wangwu", 13));
        users.add(new User("zhaoliu", 14));
        users.add(new User("tianqi", 15));
        System.out.println(esSearchService.bulkAddDocument(users, index, "1"));*/
        System.out.println(contentService.parseContent("java"));

    }

    @Test
    void termQuery() throws IOException {
        System.out.println(contentService.searchPage("java", 1, 50));
    }

    @Test
    void matchQuery() {}

    @Test
    void boolmustQuery() {}
}