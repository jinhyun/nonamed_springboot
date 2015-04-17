package io.nonamed.dao.department;

import io.nonamed.domain.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentRepository extends JpaRepository <Department,
        String> {
    List<Department> findByUpDeptCode(String deptCode);

    List<Department> findByUpDeptCodeInOrderByDepthAscDeptCodeAsc
            (List<String> upDeptCodeList);
}