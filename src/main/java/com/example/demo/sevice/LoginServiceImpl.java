package com.example.demo.sevice;

import org.apache.commons.lang3.RandomStringUtils;
import com.example.demo.dto.TokenDto;
import com.example.demo.form.LoginForm;
import com.example.demo.model.Token;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.demo.dto.TokenDto.from;

@Component
public class LoginServiceImpl implements LoginService {

    private final TokenRepository tokensRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository usersRepository;

    public LoginServiceImpl(TokenRepository tokensRepository, PasswordEncoder passwordEncoder, UserRepository usersRepository) {
        this.tokensRepository = tokensRepository;
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = usersRepository.findOneByLogin(loginForm.getLogin());
        System.out.println("userCandidate " + userCandidate);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();

            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokensRepository.save(token);
                return from(token);
            }
        } throw new IllegalArgumentException("User not found");
    }


}
