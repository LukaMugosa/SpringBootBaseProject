package me.amplitudo.stanley.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.amplitudo.stanley.domain.City;
import me.amplitudo.stanley.repository.CityRepository;
import me.amplitudo.stanley.web.errors.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    private final String ENTITY = "City";

    public City getOneById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString(), ENTITY));
    }

}
