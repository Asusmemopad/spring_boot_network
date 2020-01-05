package com.example.demo.repository;

import com.example.demo.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findOneByValue(String value);
}
