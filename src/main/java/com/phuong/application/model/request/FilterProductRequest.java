package com.phuong.application.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FilterProductRequest {
    private List<Long> brands;

    private List<Long> categories;

    private List<Integer> sizes;

    @JsonProperty("min_price")
    private Long minPrice;

    @JsonProperty("max_price")
    private Long maxPrice;

    private int page;

    public FilterProductRequest(List<Long> brandIds, List<Long> categoryIds, ArrayList<Object> objects, long minPrice, long maxValue, int page) {
    }
}
