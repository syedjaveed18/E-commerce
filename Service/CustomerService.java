package com.ChinaMarket.Ecommerce.Service;

import com.ChinaMarket.Ecommerce.Exception.CustomerNotFoundException;
import com.ChinaMarket.Ecommerce.Model.Customer;
import com.ChinaMarket.Ecommerce.RequestDTO.CustomerRequestDto;
import com.ChinaMarket.Ecommerce.RequestDTO.CustomerUpdateRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
   public String addCustomer(CustomerRequestDto customerRequestDto);

   public CustomerResponseDto getCustomerById(int customerId);

   public List<CustomerResponseDto> getAllCustomers();

   public String deleteCustomerById(int customerId);

   public CustomerResponseDto updateCustomer(CustomerUpdateRequestDto customerUpdateRequestDto);

   public CustomerResponseDto getCustomerByEmail(String email);
}
