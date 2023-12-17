package com.example.aftas.controller.vm.member.response;

import com.example.aftas.controller.vm.member.request.requestMember;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberPaginationResponse {

    private List<responseMember> members;
    private Long totalMembers;
    private int currentPage;
    private int totalPages;
}
