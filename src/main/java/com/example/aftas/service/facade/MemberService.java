package com.example.aftas.service.facade;

import com.example.aftas.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Member create(Member member);

    Member update(Member member);

    void delete(Member member);

    Optional<Member> findById(Member member);

    List<Member> findAll(int page, int pageSize);

/*    Page<Member> searchMember(String keySearch);*/
    Member findByNum(Integer num);

    Page<Member> searchMember(String keySearch, Pageable page);
}
