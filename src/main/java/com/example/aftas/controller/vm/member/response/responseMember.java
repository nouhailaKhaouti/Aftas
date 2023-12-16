package com.example.aftas.controller.vm.member.response;

import com.example.aftas.entities.Member;
import lombok.Data;

@Data
public class responseMember {
    private Integer num;

    private String name;

    private String familyName;

    private String nationality;

    private Member.IdentityDocumentType identityDocument;

    private String identityNumber;
}
