package org.wu.dashboardapi.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wu.dashboardapi.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
  Optional<Member> findTop1ById(String email);
  boolean existsById(String id);
}
