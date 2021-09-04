package com.phuong.application.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class ProductSizeId implements Serializable {
    private String productId;
    private int size;
}
