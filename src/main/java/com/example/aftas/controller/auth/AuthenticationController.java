package com.example.aftas.controller.auth;

import com.example.aftas.controller.vm.AuthenticationRequest;
import com.example.aftas.controller.vm.AuthenticationResponse;
import com.example.aftas.controller.vm.member.request.requestMember;
import com.example.aftas.entities.Member;
import com.example.aftas.service.authService.LogoutService;
import com.example.aftas.service.facade.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class AuthenticationController {

    final private MemberService memberService;
    final ModelMapper modelMapper;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody() requestMember requestmember) {
        Member member=modelMapper.map(requestmember,Member.class);
        return ResponseEntity.ok(memberService.create(member));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(memberService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        memberService.refreshToken(request, response);
    }

}
