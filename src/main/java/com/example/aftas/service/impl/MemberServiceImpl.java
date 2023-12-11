package com.example.aftas.service.impl;

import com.example.aftas.Repository.MemberRepository;
import com.example.aftas.entities.Member;
import com.example.aftas.service.facade.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;

    @Override
    public Member create(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member update(Member member) {
        return memberRepository.save(member);
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
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
