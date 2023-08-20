package com.ChinaMarket.Ecommerce.Controller;

import com.ChinaMarket.Ecommerce.Model.Seller;
import com.ChinaMarket.Ecommerce.RequestDTO.SellerRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.SellerResponseDto;
import com.ChinaMarket.Ecommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        return sellerService.addSeller(sellerRequestDto);
    }

    @GetMapping("/getseller_by_pan")
    public Seller getSellerByPan(@RequestParam String panNo){
       return sellerService.getSellerByPan(panNo);
    }

    @GetMapping("/get_all_sellers")
    public List<SellerResponseDto> getAllSellers(){
        return sellerService.getAllSellers();

    }
}
