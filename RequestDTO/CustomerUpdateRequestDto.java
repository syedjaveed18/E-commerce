package com.ChinaMarket.Ecommerce.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerUpdateRequestDto {

    private int id;

    private String name;

    private String email;

    private String mobNo;

    private int age;
}
