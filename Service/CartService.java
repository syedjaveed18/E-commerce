package com.ChinaMarket.Ecommerce.Service;

import com.ChinaMarket.Ecommerce.Exception.CustomerNotFoundException;
import com.ChinaMarket.Ecommerce.Exception.ProductNotFoundException;
import com.ChinaMarket.Ecommerce.RequestDTO.OrderRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.OrderResponseDto;

import java.util.List;

public interface CartService {
    public String addToCart(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException;

    public List<OrderResponseDto> checkOut(int customerId) throws CustomerNotFoundException;
}
