package com.shoppingApp.shoppingApp_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

@JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    private Inventory inventory;


    @Setter
    @Enumerated(EnumType.STRING)
    private Category category;

    public Product() {

    }





    public Product(Long id) {
        this.id = id;
    }

    public Product(String name, String description, Double price, Category category, Inventory inventory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.inventory = inventory;


    }

    public Product(String name, String description, Double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        //this.category = category;
    }


}