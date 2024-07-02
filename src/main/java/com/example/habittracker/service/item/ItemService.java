package com.example.habittracker.service.item;

import com.example.habittracker.dto.SearchRequestDto;
import com.example.habittracker.entity.Person;
import com.example.habittracker.model.Item;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ItemService {


    Item createIndex(Item item);

    void addItemsFromJson();

    List<Item> getAllData(String indexName);

    List<Item> searchItems(SearchRequestDto dto);
}
