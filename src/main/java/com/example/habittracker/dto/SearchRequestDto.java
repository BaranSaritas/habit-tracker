package com.example.habittracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDto {

    private List<String> fields;
    private List<String> searchValues;
}
