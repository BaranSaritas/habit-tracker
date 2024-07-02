package com.example.habittracker.service.jsonData;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.habittracker.model.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JsonDataService {
    private final ObjectMapper objectMapper;
    private final ElasticsearchClient elasticsearchClient;
    public List<Item> readItemsFromJson() {

        try {
            ClassPathResource resource = new ClassPathResource("data/items.json");
            InputStream inputStream = resource.getInputStream();
            return objectMapper.readValue(inputStream, new TypeReference<List<Item>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Item> getAllData(String indexName) {
        var query = ESUtil.createMatchAll();
        log.info("Query",query.toString());
        SearchResponse<Item> response = null;
        try {
        response =elasticsearchClient.search(
                q->q.index(indexName).query(query),Item.class);

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return extractItemsFromResponse(response);

    }


    public List<Item> extractItemsFromResponse(SearchResponse<Item> response){
        return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }
}


