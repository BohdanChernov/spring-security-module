package com.springsecurity.module.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String name;
    @OneToMany(mappedBy = "foodOrder")
    private List<OrderItem> orderItems;
    @ManyToOne
    private Customer customer;


    @Override
    public String toString() {
        return "FoodOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", customer=" + customer.getName() +
                '}';
    }
}
