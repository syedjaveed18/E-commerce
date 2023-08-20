package com.ChinaMarket.Ecommerce.Service_Impl;

import com.ChinaMarket.Ecommerce.Enum.ProductStatus;
import com.ChinaMarket.Ecommerce.Exception.CustomerNotFoundException;
import com.ChinaMarket.Ecommerce.Exception.ProductNotFoundException;
import com.ChinaMarket.Ecommerce.Model.*;
import com.ChinaMarket.Ecommerce.Repository.CustomerRepository;
import com.ChinaMarket.Ecommerce.Repository.ProductRepository;
import com.ChinaMarket.Ecommerce.RequestDTO.CardRequestDto;
import com.ChinaMarket.Ecommerce.RequestDTO.OrderRequestDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.CardResponseDto;
import com.ChinaMarket.Ecommerce.ResponseDTO.OrderResponseDto;
import com.ChinaMarket.Ecommerce.Service.CardService;
import com.ChinaMarket.Ecommerce.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public String addToCart(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException {

    Customer customer;
    try{
        customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
    }
    catch(Exception e){
        throw new CustomerNotFoundException("Invalid customer Id..!!");
    }
    Product product;
    try{
        product = productRepository.findById(orderRequestDto.getProductId()).get();
    }
    catch(Exception e){
        throw new ProductNotFoundException("Invalid Product Id..!!");
    }

    Cart cart = customer.getCart();
    int newCost = cart.getCartTotal() + orderRequestDto.getRequiredQuantity() * product.getPrice();
    cart.setCartTotal(newCost);

    // Add item to current cart
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);

        cart.getItemList().add(item);

        customerRepository.save(customer);

        return "Item has been added to your cart..!!";
}

    @Override
    public List<OrderResponseDto> checkOut(int customerId) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer Id..!!");
        }
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        int totalCost = customer.getCart().getCartTotal();

        Cart cart = customer.getCart();
        for(Item item : cart.getItemList()){

            Ordered order = new Ordered();
            order.setTotalCost(item.getRequiredQuantity() * item.getProduct().getPrice());
            order.setDeliveryCharge(0);
            order.setCustomer(customer);
            order.getItemList().add(item);

            Card card = customer.getCardList().get(0);
            String cardUsed = "";
            for(int i=0;i<card.getCardNo().length()-4;i++){
                cardUsed += 'X';
            }
            cardUsed += card.getCardNo().substring(card.getCardNo().length()-4);
            order.setCardUsedForPayment(cardUsed);

            int leftQunatity = item.getProduct().getQuantity() - item.getRequiredQuantity();
            if(leftQunatity <= 0)
                      item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQunatity);

            customer.getOrders().add(order);

            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                    .productName(item.getProduct().getProductName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(cardUsed)
                    .deliveryCharge(0)
                    .totalCost(order.getTotalCost())
                    .itemPrice(item.getProduct().getPrice())
                    .build();

            orderResponseDtos.add(orderResponseDto);
        }

        cart.setCartTotal(0);
        cart.setItemList(new ArrayList<>());

        customerRepository.save(customer);

        return orderResponseDtos;
    }
}
