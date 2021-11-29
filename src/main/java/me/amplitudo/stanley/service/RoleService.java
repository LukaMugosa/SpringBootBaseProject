package me.amplitudo.stanley.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.amplitudo.stanley.domain.Role;
import me.amplitudo.stanley.repository.RoleRepository;
import me.amplitudo.stanley.web.errors.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final String ENTITY = "Role";

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(name, ENTITY));
    }


}
