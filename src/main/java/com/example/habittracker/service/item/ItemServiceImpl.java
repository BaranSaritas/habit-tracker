package com.example.habittracker.service.item;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.habittracker.dto.SearchRequestDto;
import com.example.habittracker.model.Item;
import com.example.habittracker.repository.ItemRepository;
import com.example.habittracker.service.jsonData.ESUtil;
import com.example.habittracker.service.jsonData.JsonDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final JsonDataService dataService;
    private final ElasticsearchClient elasticsearchClient;


    @Override
    public Item createIndex(Item item) {
       return repository.save(item);
    }
    @Override
    public void addItemsFromJson() {
        log.info("Adding Items from Json");
        List<Item> Items = dataService.readItemsFromJson();
        repository.saveAll(Items);
    }

    @Override
    public List<Item> getAllData(String indexName) {
        return dataService.getAllData(indexName);
    }

    @Override
    public List<Item> searchItems(SearchRequestDto dto) {
        Supplier<Query> query = ESUtil.buildQueryForFields(dto.getFields().getFirst(),dto.getSearchValues().getFirst());
        log.info("Elasticsearch query {}",query.toString());
        SearchResponse<Item> response=null;
        try {
            response = elasticsearchClient.search(q -> q.index("items_index").query(query.get()), Item.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return extractItemsFromResponse(response);
    }

    public List<Item> extractItemsFromResponse(SearchResponse<Item> response){
        return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    }

}
