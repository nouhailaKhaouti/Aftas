package com.example.aftas.controller;


import com.example.aftas.controller.vm.member.request.requestMember;
import com.example.aftas.controller.vm.member.response.responseMember;
import com.example.aftas.entities.Member;
import com.example.aftas.service.facade.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/member")
public class MemberController {

    final private MemberService memberService;

    final ModelMapper modelMapper;

    @GetMapping("/{page}/{pageSize}/")
    public ResponseEntity<?> getAllMembers(@PathVariable("page") int page,@PathVariable("pageSize") int pageSize) {
            List<Member> members = memberService.findAll(page,pageSize);
            return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addMember(@Valid  @RequestBody() requestMember requestmember) {
            Member member=modelMapper.map(requestmember,Member.class);
            Member addedMember = memberService.create(member);
            responseMember responseMember=modelMapper.map(addedMember,responseMember.class);
            return new ResponseEntity<>(responseMember, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") long id, @RequestBody() Member member) {
            member.setId(id);
            Member addedMember = memberService.update(member);
            return new ResponseEntity<>(addedMember, HttpStatus.OK);
    }

    @GetMapping("/{search}")
    public ResponseEntity<?> search(@PathVariable("search") String search){
        List<Member> members=memberService.searchMember(search);
        List<responseMember> responseMembers=members.stream().map(m->modelMapper.map(m,responseMember.class)).toList();
        return new ResponseEntity<>(responseMembers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") long id) {

            Member member=new Member();
            member.setId(id);
            memberService.delete(member);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}
