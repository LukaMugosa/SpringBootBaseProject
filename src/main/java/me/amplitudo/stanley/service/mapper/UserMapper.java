package me.amplitudo.stanley.service.mapper;

import me.amplitudo.stanley.domain.User;
import me.amplitudo.stanley.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Mapping(source = "city", target = "city")
    User toEntity(UserDTO userDTO);

    @Mapping(source = "city", target = "city")
    UserDTO toDto(User user);
}
