package com.example.aftas.controller.vm.member.request;

import com.example.aftas.entities.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class requestMember {
    private Integer num;

    @NotNull(message = "the location can't be null")
    @NotBlank(message = "the location can't be blank")
    private String name;

/*    @NotNull(message = "the location can't be null")
    @NotBlank(message = "the location can't be blank")*/
    private String familyName;

    @NotNull(message = "the location can't be null")
    @NotBlank(message = "the location can't be blank")
    private String nationality;

    private Member.IdentityDocumentType identityDocument;

    @NotNull(message = "the location can't be null")
    @NotBlank(message = "the location can't be blank")
    private String identityNumber;
    private String email;
    private String password;

}
