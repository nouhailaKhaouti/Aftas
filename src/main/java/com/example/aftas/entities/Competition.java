package com.example.aftas.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Competition {
  @Id
  @GeneratedValue
  private Long id;

  private LocalDate date;

  private Time startTime;

  private Time endTime;

  private Integer numberOfParticipants;

  private String location;

  private Double amount;

  @OneToMany
  private List<Ranking> rankingList;

}
