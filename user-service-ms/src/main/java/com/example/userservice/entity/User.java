package com.example.userservice.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name="USER_INFORMATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    private String name;

    @Email
    private String email;


    private String paymentMethod;


    private String srcAccount;

    private  double availableAmount;




    public User(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json, User.class);
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.paymentMethod = user.getPaymentMethod();
        this.srcAccount = user.getSrcAccount();
        this.availableAmount = user.getAvailableAmount();
    }
}
