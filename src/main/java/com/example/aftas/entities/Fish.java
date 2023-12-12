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
public class Fish {
  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private String name;

  private Double averageWeight;

  @ManyToOne
  private Level level;

}
