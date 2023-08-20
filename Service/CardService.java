package com.ChinaMarket.Ecommerce.Service;

import com.ChinaMarket.Ecommerce.Exception.CustomerNotFoundException;
import com.ChinaMarket.Ecommerce.RequestDTO.CardRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.CardResponseDto;

public interface CardService {
   public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;
}
