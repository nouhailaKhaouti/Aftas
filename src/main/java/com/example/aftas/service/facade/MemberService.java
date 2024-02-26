package com.example.aftas.service.facade;

import com.example.aftas.controller.vm.AuthenticationRequest;
import com.example.aftas.controller.vm.AuthenticationResponse;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    AuthenticationResponse create(Member member);

    Member update(Member member);

    public Member updateRole(Role role, Integer num);

    void delete(Member member);

    Optional<Member> findById(Member member);

    List<Member> findAll(int page, int pageSize);

/*    Page<Member> searchMember(String keySearch);*/
    Member findByNum(Integer num);

    Page<Member> searchMember(String keySearch, Pageable page);

    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;

    public Member approveAccount(Integer num);
}
