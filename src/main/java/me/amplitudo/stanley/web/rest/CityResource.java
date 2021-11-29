package me.amplitudo.stanley.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.amplitudo.stanley.service.CityQueryService;
import me.amplitudo.stanley.service.criteria.CityCriteria;
import me.amplitudo.stanley.service.dto.CityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class CityResource {

    private final CityQueryService cityQueryService;

    /**
     * Get all cities
     * @param cityCriteria - criteria object for filtering
     * @param pageable - pageable object used for pagination
     * @return paged {@link List<CityDTO> } list of cities
     */
    @GetMapping("/cities")
    public ResponseEntity<List<CityDTO>> getCities(CityCriteria cityCriteria, Pageable pageable) {
        log.debug("Request for getting all cities by criteria: {}", cityCriteria);
        Page<CityDTO> page = cityQueryService.findByCriteria(cityCriteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


}
