package com.example.demo.sevice;

import com.example.demo.dto.TokenDto;
import com.example.demo.form.LoginForm;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
