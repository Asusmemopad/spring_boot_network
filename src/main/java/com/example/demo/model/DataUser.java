package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "data_user")
public class DataUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataUserId;

    private String phoneNumber;
    private String email;
}
