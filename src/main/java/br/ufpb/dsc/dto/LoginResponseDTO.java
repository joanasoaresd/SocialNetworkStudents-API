package br.ufpb.dsc.dto;

import lombok.Getter;

@Getter
public class LoginResponseDTO {
    private String message;

  public LoginResponseDTO(String message) {
    this.message = message;
  }
}
