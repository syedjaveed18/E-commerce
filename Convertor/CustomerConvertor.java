package com.ChinaMarket.Ecommerce.Convertor;

import com.ChinaMarket.Ecommerce.Model.Customer;
import com.ChinaMarket.Ecommerce.RequestDTO.CustomerRequestDto;
import com.ChinaMarket.Ecommerce.RequestDTO.CustomerUpdateRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.CustomerResponseDto;

public class CustomerConvertor {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto customerTOCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .eamil(customer.getEmail())
                .mobNo(customer.getMobNo())
                .build();
    }

  /*  public static Customer customerUpdateRequestDtoToCustomer(CustomerUpdateRequestDto customerUpdateRequestDto){
        return Customer.builder()
                .name(customerUpdateRequestDto.getName())
                .email(customerUpdateRequestDto.getEmail())
                .age(customerUpdateRequestDto.getAge())
                .mobNo(customerUpdateRequestDto.getMobNo())
                .build();
    }

   */
}
