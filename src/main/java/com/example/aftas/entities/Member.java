package com.example.aftas.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
  @Id
  @GeneratedValue
  private Long id;

  private Integer num;
  private String name;
  private String familyName;
  private LocalDate accessionDate;
  private String nationality;
  @Enumerated(EnumType.STRING)
  private IdentityDocumentType identityDocument;
  private String identityNumber;

  public enum IdentityDocumentType {
    CIN,
    CARTE_RESIDENCE,
    PASSPORT

  }
}
