package me.amplitudo.stanley.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.amplitudo.stanley.domain.City;
import me.amplitudo.stanley.domain.Role;
import me.amplitudo.stanley.domain.User;
import me.amplitudo.stanley.security.AuthorityConstants;
import me.amplitudo.stanley.service.dto.RegistrationDTO;
import me.amplitudo.stanley.service.dto.UserDTO;
import me.amplitudo.stanley.service.mapper.CityMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationService {

    private final CityService cityService;

    private final RoleService roleService;

    private final CityMapper cityMapper;

    private final UserService userService;

    public UserDTO registerUser(RegistrationDTO registrationDTO) {
        userService.checkIfEmailExists(registrationDTO.getEmail());
        City city = cityService.getOneById(registrationDTO.getCityId());
        Role role = roleService.getRoleByName(AuthorityConstants.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        UserDTO newUser = UserDTO.builder()
                .firstName(registrationDTO.getFirstName())
                .lastName(registrationDTO.getLastName())
                .fullName(registrationDTO.getFirstName() + " " + registrationDTO.getLastName())
                .email(registrationDTO.getEmail())
                .city(cityMapper.toDto(city))
                .username(
                    registrationDTO.getFirstName().toLowerCase(Locale.ROOT) +
                        "." +
                        registrationDTO.getLastName() +
                        String.format("%06d", new Random().nextInt(999999))
                )
                .roles(roles)
                .build();
        return userService.saveNewUser(newUser, registrationDTO.getPassword());
    }


}
