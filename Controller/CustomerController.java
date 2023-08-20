package com.ChinaMarket.Ecommerce.Controller;

import com.ChinaMarket.Ecommerce.Model.Customer;
import com.ChinaMarket.Ecommerce.RequestDTO.CustomerRequestDto;
import com.ChinaMarket.Ecommerce.RequestDTO.CustomerUpdateRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.CustomerResponseDto;
import com.ChinaMarket.Ecommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/get_customer_by_id/{customerId}")
    public CustomerResponseDto getCustomerById(@PathVariable int customerId) {

        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);

        return customerResponseDto;

    }

    @GetMapping("/get_all_customers")
    public List<CustomerResponseDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/delete_customer_by_id/{customerId}")
    public String deleteCustomerById(@PathVariable int customerId){
       return customerService.deleteCustomerById(customerId);
    }

    @PutMapping("/update_customer")
    public CustomerResponseDto updateCustomer(@RequestBody CustomerUpdateRequestDto customerUpdateRequestDto){
        return customerService.updateCustomer(customerUpdateRequestDto);
    }

    @GetMapping("/get_customer_by_email/{email}")
    public CustomerResponseDto getCustomerByEmail(@PathVariable String email){
        return customerService.getCustomerByEmail(email);
    }
}
