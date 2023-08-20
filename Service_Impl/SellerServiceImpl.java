package com.ChinaMarket.Ecommerce.Service_Impl;

import com.ChinaMarket.Ecommerce.Convertor.SellerConvertor;
import com.ChinaMarket.Ecommerce.Model.Seller;
import com.ChinaMarket.Ecommerce.Repository.SellerRepository;
import com.ChinaMarket.Ecommerce.RequestDTO.SellerRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.SellerResponseDto;
import com.ChinaMarket.Ecommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public String addSeller(SellerRequestDto sellerRequestDto) {

        Seller seller = SellerConvertor.sellerRequestDtoToSeller(sellerRequestDto);
        sellerRepository.save(seller);
        return "Congrats! Now you can sell on China Market  !!!";
    }

    @Override
    public Seller getSellerByPan(String panNo) {
        Seller seller = sellerRepository.findByPanNo(panNo);
        return seller;
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {

        List<Seller> sellers = sellerRepository.findAll();

        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        for(Seller seller1 : sellers){
            SellerResponseDto sellerResponseDto = new SellerResponseDto();
            sellerResponseDto.setName(seller1.getName());
            sellerResponseDto.setEmail(seller1.getEmail());
            sellerResponseDto.setMobNo(seller1.getMobNo());
            sellerResponseDto.setPanNo(seller1.getPanNo());
            sellerResponseDto.setProductList(seller1.getProductList());

            sellerResponseDtos.add(sellerResponseDto);
        }
        return sellerResponseDtos;
    }
}
