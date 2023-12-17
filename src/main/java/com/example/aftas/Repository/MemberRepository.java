package com.example.aftas.Repository;

import com.example.aftas.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByNum(Integer num);
    Page<Member> findByNumOrNameOrFamilyName(Integer num, String name, String familyName, Pageable page);
}
