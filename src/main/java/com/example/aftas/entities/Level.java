package com.example.aftas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Level {
  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private Integer code;

  private String description;

  @Column(unique = true)
  private Integer points;

}
