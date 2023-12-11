package com.example.aftas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hunting {
  @Id
  @GeneratedValue
  private Long id;

  private Integer numberOfFish;

  @ManyToOne
  private Competition competition;

  @ManyToOne
  private Member member;

  @ManyToOne
  private Fish fish;

}
