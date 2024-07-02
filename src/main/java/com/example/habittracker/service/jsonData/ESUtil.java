package com.example.habittracker.service.jsonData;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.util.ObjectBuilder;
import lombok.experimental.UtilityClass;

import java.util.function.Function;
import java.util.function.Supplier;


@UtilityClass
public class ESUtil {
    public static Query createMatchAll() {
        return Query.of(x->x.matchAll(new MatchAllQuery.Builder().build()));
    }

    public static Supplier<Query> buildQueryForFields(String fieldName, String searchValue) {
        return () -> Query.of(x->x.match(buildMatchQueryForFields(fieldName, searchValue)));
    }

    private static MatchQuery buildMatchQueryForFields(String fieldName, String searchValue) {
        return new MatchQuery.Builder().field(fieldName).query(searchValue).build();
    }
}
