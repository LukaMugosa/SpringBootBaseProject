package me.amplitudo.stanley.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.amplitudo.stanley.domain.User;
import me.amplitudo.stanley.repository.UserRepository;
import me.amplitudo.stanley.service.dto.UserDTO;
import me.amplitudo.stanley.service.mapper.UserMapper;
import me.amplitudo.stanley.web.errors.BadActionException;
import me.amplitudo.stanley.web.errors.ExceptionErrors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserDTO saveNewUser(UserDTO userDTO, String password) {
         User user = userMapper.toEntity(userDTO);
         user.setPassword(passwordEncoder.encode(password));
         user = userRepository.save(user);
         return userMapper.toDto(user);
    }

    public void checkIfEmailExists(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new BadActionException(
                    ExceptionErrors.EMAIL_EXISTS.getCode(),
                    ExceptionErrors.EMAIL_EXISTS.getDescription()
            );
        }
    }

}
