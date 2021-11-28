package me.amplitudo.stanley.web.vm;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginVM {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Min(8)
    private String password;


}
