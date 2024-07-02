package com.example.habittracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

// @Entity
@Document(indexName = "persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    private String id;
    @Field(name = "name",type = FieldType.Text)
    private String name;
    @Field(name = "surname",type = FieldType.Text)
    private String surname;
    @Field(name = "address",type = FieldType.Text)
    private String address;
    @Field(name = "dateTime",type = FieldType.Date)
    private Date dateTime;

}
