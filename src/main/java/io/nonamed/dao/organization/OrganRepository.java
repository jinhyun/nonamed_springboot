package io.nonamed.dao.organization;

import io.nonamed.domain.organization.Organ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganRepository extends JpaRepository <Organ, Integer> {
}
