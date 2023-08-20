package com.ChinaMarket.Ecommerce.ResponseDTO;

import com.ChinaMarket.Ecommerce.Enum.ProductCategory;
import com.ChinaMarket.Ecommerce.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {

    private String productName;

    private int price;

    private ProductCategory productCategory;

    private ProductStatus productStatus;
}

