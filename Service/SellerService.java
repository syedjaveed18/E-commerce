package com.ChinaMarket.Ecommerce.Service;

import com.ChinaMarket.Ecommerce.Model.Seller;
import com.ChinaMarket.Ecommerce.RequestDTO.SellerRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.SellerResponseDto;

import java.util.List;

public interface SellerService {

    public String addSeller(SellerRequestDto sellerRequestDto);

   public Seller getSellerByPan(String panNo);

   public List<SellerResponseDto> getAllSellers();
}
