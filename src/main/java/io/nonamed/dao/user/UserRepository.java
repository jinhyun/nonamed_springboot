package io.nonamed.dao.user;

import io.nonamed.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long>{
    List<User> findByDeptCode(String deptCode);

    List<User> findByDeptCodeIn(List<String> upDeptCodeList);
}