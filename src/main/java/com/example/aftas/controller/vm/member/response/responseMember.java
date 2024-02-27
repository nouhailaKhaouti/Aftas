package com.example.aftas.controller.vm.member.response;

import com.example.aftas.entities.Member;
import com.example.aftas.entities.Role;
import lombok.Data;

@Data
public class responseMember {
    private Integer num;

    private String name;

    private String familyName;

    private String nationality;

    private Member.IdentityDocumentType identityDocument;

    private String identityNumber;

    private Boolean accountApproved= false;

    private Role role;
}
