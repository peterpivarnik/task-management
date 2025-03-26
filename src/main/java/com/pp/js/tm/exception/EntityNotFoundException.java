package com.pp.js.tm.exception;

/**
 * Exception thrown in case entity is not found.
 */
public class EntityNotFoundException extends TaskManagementException {

  /**
   * Default constructor.
   *
   * @param message exception message
   */
  public EntityNotFoundException(String message) {
    super(message);
  }
}
