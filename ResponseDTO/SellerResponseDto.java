package com.ChinaMarket.Ecommerce.ResponseDTO;

import com.ChinaMarket.Ecommerce.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerResponseDto {
    private String name;

    private String email;

    private String mobNo;

    private String panNo;

    List<Product> productList;
}
