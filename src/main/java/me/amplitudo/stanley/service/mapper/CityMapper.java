package me.amplitudo.stanley.service.mapper;

import me.amplitudo.stanley.domain.City;
import me.amplitudo.stanley.service.dto.CityDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link City} and its DTO {@link CityDTO}.
 */
@Mapper(componentModel = "spring")
public interface CityMapper extends EntityMapper<CityDTO, City> {

    CityDTO toDto(City s);

    City toEntity(CityDTO cityDTO);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CityDTO toDtoId(City city);
}
