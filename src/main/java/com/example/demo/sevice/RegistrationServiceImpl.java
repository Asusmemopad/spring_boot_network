package com.example.demo.sevice;

import com.example.demo.form.UserForm;
import com.example.demo.model.Role;
import com.example.demo.model.State;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    final private PasswordEncoder passwordEncoder;
    final private UserRepository userRepository;

    public RegistrationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public String registration(UserForm userForm) {
        String message;
        Optional<User> userExist = userRepository.findOneByLogin(userForm.getLogin());

        if (!userExist.isPresent()){
            User user = User.builder()
                    .firstName(userForm.getFirstName())
                    .lastName(userForm.getLastName())
                    .login(userForm.getLogin())
                    .hashPassword(passwordEncoder.encode(userForm.getPassword()))
                    .role(Role.USER)
                    .state(State.ACTIVE)
                    .build();
            userRepository.save(user);
            message = "Ви зареєстровані. Тепер увійдіть під своїм логіном";
        } else {
            message = "Користувач з таким логіном вже існує";
        }

        return message;
    }

}
