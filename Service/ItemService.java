package com.ChinaMarket.Ecommerce.Service;

import com.ChinaMarket.Ecommerce.Exception.ProductNotFoundException;
import com.ChinaMarket.Ecommerce.ResponseDTO.ItemResponseDto;

public interface ItemService {
    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException;
}
