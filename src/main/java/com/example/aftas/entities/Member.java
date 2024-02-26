package com.example.aftas.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements UserDetails {
  @Id
  @GeneratedValue
  private Long id;

  private Integer num;

  private String name;

  private String familyName;

  private LocalDate accessionDate;

  private String nationality;
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private IdentityDocumentType identityDocument;

  private String identityNumber;

  @Enumerated(EnumType.STRING)
  private Role role;

  private boolean accountApproved = false;


  public boolean isAccountApproved() {
    return accountApproved;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountApproved;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public enum IdentityDocumentType {
    CIN,
    CARTE_RESIDENCE,
    PASSPORT

  }
}
