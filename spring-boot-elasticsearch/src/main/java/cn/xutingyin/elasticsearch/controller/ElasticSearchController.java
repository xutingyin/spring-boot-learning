package cn.xutingyin.elasticsearch.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.xutingyin.elasticsearch.service.EsSearchService;

@RestController
@RequestMapping("/esController")
public class ElasticSearchController {
    @Autowired
    private EsSearchService esSearchService;

    @RequestMapping("query")
    public String testQuery(@RequestParam String keyWord) throws IOException {
        String index = "jd";
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("title", keyWord);
        List<Map<String, Object>> maps = esSearchService.fuzzyQuery(index, treeMap, 15, 0, true);
        JSONObject json = new JSONObject();
        json.put("data", maps);
        return json.toJSONString();
    }
}
