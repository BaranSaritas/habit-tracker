package com.example.habittracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@Document(indexName = "items_index")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    private String id;
    @Field(name="name",type= FieldType.Text)
    private String name;
    @Field(name="price",type= FieldType.Double)
    private Double price;
    @Field(name="brand",type= FieldType.Text)
    private String brand;
    @Field(name="category",type= FieldType.Keyword)
    private String category;

}
