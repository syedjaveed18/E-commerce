package com.ChinaMarket.Ecommerce.Convertor;

import com.ChinaMarket.Ecommerce.Model.Seller;
import com.ChinaMarket.Ecommerce.RequestDTO.SellerRequestDto;

public class SellerConvertor {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

       return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .panNo(sellerRequestDto.getPanNo())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }
}
