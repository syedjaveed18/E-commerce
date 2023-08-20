package com.ChinaMarket.Ecommerce.Service_Impl;

import com.ChinaMarket.Ecommerce.Enum.ProductStatus;
import com.ChinaMarket.Ecommerce.Exception.CustomerNotFoundException;
import com.ChinaMarket.Ecommerce.Exception.ProductNotFoundException;
import com.ChinaMarket.Ecommerce.Model.*;
import com.ChinaMarket.Ecommerce.Repository.CustomerRepository;
import com.ChinaMarket.Ecommerce.Repository.ProductRepository;
import com.ChinaMarket.Ecommerce.RequestDTO.OrderRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.OrderResponseDto;
import com.ChinaMarket.Ecommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotFoundException("Invalid Customer Id..!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new ProductNotFoundException("Invalid Product Id..!!");
        }

        if(product.getQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry!..Required Quantity not Available");
        }
        int deliveryCharge =0;
        int totalcost = orderRequestDto.getRequiredQuantity() * product.getPrice();
        if(totalcost < 500){
            deliveryCharge = 40;
            totalcost += deliveryCharge;
        }
        Ordered order = new Ordered();
        order.setDeliveryCharge(deliveryCharge);
        order.setTotalCost(totalcost);

        Card card = customer.getCardList().get(0);
        String cardUsed = "";
        for(int i=0;i<card.getCardNo().length()-4; i++){
            cardUsed += 'X';
        }

        cardUsed += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardUsed);

        Item item = new Item();
        item.setOrder(order);
        order.getItemList().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        if(leftQuantity <= 0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

         customer.getOrders().add(order);
         Customer savedCustomer = customerRepository.save(customer);
         Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);

         //prepare ResponseDto
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getProductName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .itemPrice(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(40)
                .cardUsedForPayment(cardUsed)
                .build();

        return orderResponseDto;
    }
}
