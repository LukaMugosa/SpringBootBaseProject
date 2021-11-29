package me.amplitudo.stanley.service.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class RegistrationDTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Long cityId;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

}
