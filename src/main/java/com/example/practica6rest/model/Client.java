package com.example.practica6rest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "client")
public class Client {
//    @Todo Add roles and maybe create User entity for security
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 5000)
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private Integer phone_number;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("client_purchases")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("client_reservations")
    private List<Reservation> reservations;

    @Setter(AccessLevel.NONE)
    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "created_by")
    private String createdBy;

    @Setter(AccessLevel.NONE)
    @Column(name = "update_date")
    @UpdateTimestamp
    private Date updateDate;

    @Column(name = "updated_by")
    private String updatedBy;

    public Client(String name, String password, String email, Integer phone_number) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
    }

    public Client() {
    }

}
