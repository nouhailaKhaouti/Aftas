package com.example.aftas.entities;


import com.example.aftas.embedded.RankingPrimaryKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ranking {

  @EmbeddedId
  private RankingPrimaryKey id;

  @Column(columnDefinition = "integer default 0")
  private Integer rank=0;

  @Column(columnDefinition = "integer default 0")
  private Integer score=0;

  @ManyToOne
  @JoinColumn(name = "competition_id")
  @MapsId("competitionId")
  private Competition competition;

  @ManyToOne
  @JoinColumn(name = "member_id")
  @MapsId("memberId")
  private Member member;
}
