package com.jt.Service;

import com.jt.pojo.Item;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {
    @Resource
    private TransportClient client;

    public List<Item> termSearch(String title, String value) {
        SearchRequestBuilder requestBuilder = client.prepareSearch("jtdb").setTypes("tb_item");
        SearchResponse searchResponse = requestBuilder.setQuery(QueryBuilders.matchQuery(title, value))
                .setFrom(0)
                .setSize(10)
                .setExplain(true)
                .execute()
                .actionGet();
        SearchHits hits = searchResponse.getHits();
        List<Item> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            Item item = new Item();
            Map<String, Object> source = hit.getSource();

            item.setId(Long.parseLong(source.get("id").toString()));
            item.setTitle((String) source.get("title"));
            list.add(item);
        }
        return list;
    }
}
