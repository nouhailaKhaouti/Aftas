package com.example.aftas.embedded;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankingPrimaryKey implements Serializable {

    @Column(name = "competition_id")
    private Long competitionId;

    @Column(name = "member_id")
    private Long memberId;
}
