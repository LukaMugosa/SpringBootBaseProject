package me.amplitudo.stanley.service.dto;

import lombok.*;
import me.amplitudo.stanley.domain.Role;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String email;
    private CityDTO city;
    private Set<Role> roles = new HashSet<>();
}
