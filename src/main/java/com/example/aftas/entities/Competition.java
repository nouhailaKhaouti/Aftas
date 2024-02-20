package com.example.aftas.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Competition {
  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  private LocalDate date;

  @Column(columnDefinition = "time without time zone default 08:00:00")
  private LocalTime startTime;

  @Column(unique = true)
  private String code;

  @Column(columnDefinition = "time without time zone default 18:00:00")
  private LocalTime endTime;

  @Column(columnDefinition="Integer default 0")
  private Integer numberOfParticipants;

  private String location;

  private Double amount;

  @OneToMany(fetch = FetchType.EAGER)
  private List<Ranking> rankings;

}
