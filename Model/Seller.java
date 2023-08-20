package com.ChinaMarket.Ecommerce.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobNo;

    @Column(unique = true)
    private String panNo;

    @OneToMany(mappedBy = "seller" , cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();
}
