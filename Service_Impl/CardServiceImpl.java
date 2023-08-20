package com.ChinaMarket.Ecommerce.Service_Impl;

import com.ChinaMarket.Ecommerce.Exception.CustomerNotFoundException;
import com.ChinaMarket.Ecommerce.Model.Card;
import com.ChinaMarket.Ecommerce.Model.Customer;
import com.ChinaMarket.Ecommerce.Repository.CustomerRepository;
import com.ChinaMarket.Ecommerce.RequestDTO.CardRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.CardDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.CardResponseDto;
import com.ChinaMarket.Ecommerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer ;
        try {
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
          }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        //make a card object;
        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .customer(customer)
                .build();

        //add the card to the current card list of customer
        customer.getCardList().add(card);

        customerRepository.save(customer); //saves both customer and card

        //prepare ResponseDto
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setName(customer.getName());

        //convert every card to CardDtos
        List<CardDto> cardDtoList = new ArrayList<>();
        for(Card card1 : customer.getCardList()){
            CardDto cardDto = new CardDto();
            cardDto.setCardNo(card1.getCardNo());
            cardDto.setCardType(card1.getCardType());

            cardDtoList.add(cardDto);
        }

        cardResponseDto.setCardDtos(cardDtoList);

         return cardResponseDto;
       }
}
