package com.greenfoxacademy.petpal.exception;

public class AnimalAlreadyAdoptedException extends Exception {
  public AnimalAlreadyAdoptedException(String message) {
    super(message);
  }
}
