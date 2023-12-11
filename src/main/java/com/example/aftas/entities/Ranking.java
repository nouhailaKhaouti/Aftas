package com.example.aftas.entities;


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
  @Id
  @GeneratedValue

  private Long id;

  @Column(columnDefinition = "integer default 0")
  private Integer rank;

  @Column(columnDefinition = "integer default 0")
  private Integer score;

  @ManyToOne
  private Competition competition;

  @ManyToOne
  private Member member;
}
