package com.ChinaMarket.Ecommerce.Service_Impl;

import com.ChinaMarket.Ecommerce.Convertor.CustomerConvertor;
import com.ChinaMarket.Ecommerce.Exception.CustomerNotFoundException;
import com.ChinaMarket.Ecommerce.Model.Card;
import com.ChinaMarket.Ecommerce.Model.Cart;
import com.ChinaMarket.Ecommerce.Model.Customer;
import com.ChinaMarket.Ecommerce.Model.Ordered;
import com.ChinaMarket.Ecommerce.Repository.CardRepository;
import com.ChinaMarket.Ecommerce.Repository.CartRepository;
import com.ChinaMarket.Ecommerce.Repository.CustomerRepository;
import com.ChinaMarket.Ecommerce.Repository.OrderedRepository;
import com.ChinaMarket.Ecommerce.RequestDTO.CustomerRequestDto;
import com.ChinaMarket.Ecommerce.RequestDTO.CustomerUpdateRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.CustomerResponseDto;
import com.ChinaMarket.Ecommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderedRepository orderedRepository;

    @Override
    public String addCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDto);

        // Allocate a cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);
        customerRepository.save(customer);
        return "Congrats !! Welcome to the China Market..!";
    }

    @Override
    public CustomerResponseDto getCustomerById(int customerId) {

        Customer customer = customerRepository.findById(customerId).get();
        CustomerResponseDto customerResponseDto = CustomerConvertor.customerTOCustomerResponseDto(customer);
        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();

        for(Customer customer1 : customers){
            CustomerResponseDto customerResponseDto = CustomerConvertor.customerTOCustomerResponseDto(customer1);

            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }

    @Override
    public String deleteCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId).get();

        Cart cart = customer.getCart();
        cartRepository.deleteById(cart.getId());

        List<Card> cardList = customer.getCardList();
        for(Card card1 : cardList){
            cardRepository.deleteById(card1.getId());
        }

        List<Ordered> orders = customer.getOrders();
        for(Ordered order1 : orders){
            orderedRepository.deleteById(order1.getId());
        }

        customerRepository.deleteById(customerId);
        return "Customer Deleted Successfully..!!";
    }

    @Override
    public CustomerResponseDto updateCustomer(CustomerUpdateRequestDto customerUpdateRequestDto) {
        Customer customer = customerRepository.findById(customerUpdateRequestDto.getId()).get();

              customer.setName(customerUpdateRequestDto.getName());
              customer.setEmail(customerUpdateRequestDto.getEmail());
              customer.setMobNo(customerUpdateRequestDto.getMobNo());
              customer.setAge(customerUpdateRequestDto.getAge());

        customerRepository.save(customer);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto = CustomerConvertor.customerTOCustomerResponseDto(customer);
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);

        CustomerResponseDto customerResponseDto = CustomerConvertor.customerTOCustomerResponseDto(customer);
        return customerResponseDto;
    }
}
