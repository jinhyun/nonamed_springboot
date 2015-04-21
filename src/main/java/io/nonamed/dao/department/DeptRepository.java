package io.nonamed.dao.department;

import io.nonamed.domain.department.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository <Dept, String> {
}