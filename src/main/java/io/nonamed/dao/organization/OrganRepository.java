package io.nonamed.dao.organization;

import io.nonamed.domain.organization.Organ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganRepository extends JpaRepository <Organ, Integer> {
//    @Query(value = "SELECT a.* FROM ORGAN a LEFT JOIN USER b ON a.USER_ID = b.USER_ID", nativeQuery = true)
//    @Query("SELECT a.organId, a.organDeptName FROM Organ a LEFT JOIN a.users b")
//    @Query("SELECT a FROM Organ a JOIN FETCH a.users b")
//    @Query("SELECT a FROM Organ a LEFT JOIN a.users b")
//    List<Organ> findOrgan();
}