package com.example.aftas.controller;


import com.example.aftas.entities.Member;
import com.example.aftas.service.facade.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("Aftas/api/member")
public class MemberController {

    final private MemberService memberService;

    final ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<?> getAllMembers() {
            List<Member> members = memberService.findAll();
            return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addMember(@RequestBody() Member member) {
            Member addedMember = memberService.create(member);
            return new ResponseEntity<>(addedMember, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") long id, @RequestBody() Member member) {
            member.setId(id);
            Member addedMember = memberService.update(member);
            return new ResponseEntity<>(addedMember, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") long id) {

            Member member=new Member();
            member.setId(id);
            memberService.delete(member);

            return new ResponseEntity<>(HttpStatus.OK);
    }

}
