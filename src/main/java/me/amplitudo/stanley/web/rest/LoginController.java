package me.amplitudo.stanley.web.rest;

import me.amplitudo.stanley.web.errors.ExceptionErrors;
import me.amplitudo.stanley.web.errors.InvalidCredentialsException;
import me.amplitudo.stanley.web.vm.JwtResponseVM;
import me.amplitudo.stanley.web.vm.LoginVM;
import me.amplitudo.stanley.security.CustomUserDetailsService;
import me.amplitudo.stanley.util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userService;

    @GetMapping("/")
    public String home() {
        return "Welcome to Baseproject!";
    }


    @PostMapping("/authenticate")
    public JwtResponseVM authenticate(@Valid @RequestBody LoginVM jwtRequest) throws UsernameNotFoundException{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException(
                    ExceptionErrors.INVALID_CREDENTIALS.getCode(),
                    ExceptionErrors.INVALID_CREDENTIALS.getDescription()
            );
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getEmail());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JwtResponseVM(token);
    }

}
