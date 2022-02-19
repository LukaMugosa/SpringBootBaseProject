package me.amplitudo.stanley.web.rest;

import lombok.RequiredArgsConstructor;
import me.amplitudo.stanley.service.RegistrationService;
import me.amplitudo.stanley.service.dto.RegistrationDTO;
import me.amplitudo.stanley.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Validated @RequestBody RegistrationDTO registrationDTO) throws URISyntaxException {
        UserDTO registeredUser = registrationService.registerUser(registrationDTO);
        return ResponseEntity
                .created(new URI("/api/register/" + registeredUser.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("Stanley", true, "UserDTO", registeredUser.getId().toString()))
                .body(registeredUser);
    }


}
