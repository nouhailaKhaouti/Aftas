package com.example.aftas.service.impl;

import com.example.aftas.Repository.MemberRepository;
import com.example.aftas.Repository.TokenRepository;
import com.example.aftas.controller.vm.AuthenticationRequest;
import com.example.aftas.controller.vm.AuthenticationResponse;
import com.example.aftas.entities.*;
import com.example.aftas.exception.AlreadyExistException;
import com.example.aftas.exception.NotFoundException;
import com.example.aftas.service.facade.MemberService;
import com.example.aftas.service.jwtService.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse create(Member member) {
        if(memberRepository.findMemberByNum(member.getNum())==null)
        {
            member.setAccessionDate(LocalDate.now());
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            member.setRole(Role.ADHERENT);
            Member savedUser=memberRepository.save(member);
            var jwtToken = jwtService.generateToken(member);
            var refreshToken = jwtService.generateRefreshToken(member);
            saveUserToken(savedUser, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        throw new AlreadyExistException();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = memberRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(Member user, String jwtToken) {
        var token = Token.builder()
                .member(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Member user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.memberRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
    @Override
    public Member update(Member member) {
        if(memberRepository.findMemberByNum(member.getNum())!=null)
        {
            return memberRepository.save(member);
        }
        throw new NotFoundException();
    }

    @Override
    public Member updateRole(Role role,Integer num) {
        Member member=memberRepository.findMemberByNum(num);
        if(member!=null)
        {
            member.setRole(role);
            return memberRepository.save(member);
        }
        throw new NotFoundException();
    }


    @Override
    public Member approveAccount(Integer num) {
        Member member = memberRepository.findMemberByNum(num);
        if (member != null) {
            member.setAccountApproved(true);
           return  memberRepository.save(member);
        } else {
            throw new NotFoundException();
        }
    }
    @Override
    public Member findByNum(Integer num){
        return memberRepository.findMemberByNum(num);
    }
    @Override
    public void delete(Member member) {
         memberRepository.delete(member);
    }

    @Override
    public Optional<Member> findById(Member member) {
        return memberRepository.findById(member.getId());
    }

    @Override
    public List<Member> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Member> memberPage = memberRepository.findAll(pageable);
        return memberPage.getContent();
    }
    @Override
    public Page<Member> searchMember(String keySearch, Pageable page) {
        if(keySearch!=null) {
            if (keySearch.matches("\\d+"))
                return memberRepository.findByNumOrNameOrFamilyName(Integer.valueOf(keySearch), "", "",page);
            else
                return memberRepository.findByNumOrNameOrFamilyName(null, keySearch, keySearch,page);
        }
        return memberRepository.findAll(page);
    }
}
