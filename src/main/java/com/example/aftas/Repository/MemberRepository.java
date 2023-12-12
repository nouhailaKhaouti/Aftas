package com.example.aftas.Repository;

import com.example.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByNum(Integer num);
    List<Member> findByNumOrNameOrFamilyName(Integer num,String name,String familyName);
}
