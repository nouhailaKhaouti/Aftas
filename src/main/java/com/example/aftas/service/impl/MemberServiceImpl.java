package com.example.aftas.service.impl;

import com.example.aftas.Repository.MemberRepository;
import com.example.aftas.entities.Member;
import com.example.aftas.exception.AlreadyExistException;
import com.example.aftas.exception.NotFoundException;
import com.example.aftas.service.facade.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    final private MemberRepository memberRepository;

    @Override
    public Member create(Member member) {
        if(memberRepository.findMemberByNum(member.getNum())==null)
        {
            member.setAccessionDate(LocalDate.now());
            return memberRepository.save(member);
        }
        throw new AlreadyExistException();
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
    public List<Member> searchMember(String keySearch) {
        if(keySearch.matches("\\d+"))
            return this.memberRepository.findByNumOrNameOrFamilyName(Integer.valueOf(keySearch), "", "");
        else
            return this.memberRepository.findByNumOrNameOrFamilyName(null, keySearch, keySearch);
    }
}
