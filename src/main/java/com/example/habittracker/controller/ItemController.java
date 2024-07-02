package com.example.habittracker.controller;

import com.example.habittracker.dto.SearchRequestDto;
import com.example.habittracker.entity.Person;
import com.example.habittracker.model.Item;
import com.example.habittracker.service.item.ItemService;
import com.example.habittracker.service.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping
    public Item createItems(@RequestBody Item item){
       return service.createIndex(item);
    }


    @PostMapping("/init-index")
    public void addItemsFromJson(){
        service.addItemsFromJson();
    }


    @GetMapping("/getAllData/{indexName}")
    public List<Item> getAllData (@PathVariable String indexName){
        return service.getAllData(indexName);
    }

    @GetMapping("/search")
    public List<Item> searchItems(@RequestBody SearchRequestDto dto){
        return service.searchItems(dto);
    }

}
