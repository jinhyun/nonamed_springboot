package io.nonamed.service.organization;

import io.nonamed.dao.organization.OrganRepository;
import io.nonamed.domain.organization.Organ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganService {
    @Autowired
    OrganRepository organRepository;

    public List<Organ> getOrganAllList() {
        return organRepository.findAll();
    }
}
