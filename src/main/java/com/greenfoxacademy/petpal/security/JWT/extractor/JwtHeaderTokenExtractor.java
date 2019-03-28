package com.greenfoxacademy.petpal.security.JWT.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {

  @Override
  public String extract(String header) {
    if (header == null) {
      throw new AuthenticationServiceException("Authorization header cannot be blank!");
    }
    String HEADER_PREFIX = "Bearer ";
    return header.substring(HEADER_PREFIX.length(), header.length());
  }
}
