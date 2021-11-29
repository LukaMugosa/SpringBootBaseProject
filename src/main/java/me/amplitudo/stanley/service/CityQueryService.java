package me.amplitudo.stanley.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.amplitudo.stanley.domain.City;
import me.amplitudo.stanley.domain.City_;
import me.amplitudo.stanley.repository.CityRepository;
import me.amplitudo.stanley.service.criteria.CityCriteria;
import me.amplitudo.stanley.service.dto.CityDTO;
import me.amplitudo.stanley.service.mapper.CityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * A service class for getting all cities based on criteria
 */
@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CityQueryService extends QueryService<City> {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    /**
     * Return a {@link Page} of {@link CityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CityDTO> findByCriteria(CityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<City> specification = createSpecification(criteria);
        Page<City> cities = cityRepository.findAll(specification, page);
        return cities.map(cityMapper::toDto);
    }


    /**
     * Custom create specification function
     * @param cityCriteria - criteria object
     * @return - generated {@link Specification<City>} specification from criteria object
     */
    private Specification<City> createSpecification(CityCriteria cityCriteria) {

        Specification<City> citySpecification = Specification.where(null);
        if(cityCriteria != null) {
            if(cityCriteria.getId() != null) {
                citySpecification = citySpecification.and(buildRangeSpecification(cityCriteria.getId(), City_.id));
            }
            if(cityCriteria.getName() != null) {
                citySpecification = citySpecification.and(buildStringSpecification(cityCriteria.getName(), City_.name));
            }
        }

        return citySpecification;
    }

}
