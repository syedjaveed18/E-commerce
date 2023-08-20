package com.ChinaMarket.Ecommerce.Service_Impl;

import com.ChinaMarket.Ecommerce.Convertor.ProductConvertor;
import com.ChinaMarket.Ecommerce.Enum.ProductCategory;
import com.ChinaMarket.Ecommerce.Exception.SellerNotFoundException;
import com.ChinaMarket.Ecommerce.Model.Product;
import com.ChinaMarket.Ecommerce.Model.Seller;
import com.ChinaMarket.Ecommerce.Repository.ProductRepository;
import com.ChinaMarket.Ecommerce.Repository.SellerRepository;
import com.ChinaMarket.Ecommerce.RequestDTO.ProductRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.ProductResponseDto;
import com.ChinaMarket.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {

        Seller seller;

        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch(Exception e){
            throw new SellerNotFoundException("Invalid Seller Id");
        }

        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);

        product.setSeller(seller);
        seller.getProductList().add(product);

        sellerRepository.save(seller);

        ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(ProductCategory productCategory) {
     List<Product> products = productRepository.findAllByProductCategory(productCategory);

     List<ProductResponseDto> productResponseDtos = new ArrayList<>();

     for(Product product : products){
         ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);

         productResponseDtos.add(productResponseDto);
     }
     return productResponseDtos;
    }
}
