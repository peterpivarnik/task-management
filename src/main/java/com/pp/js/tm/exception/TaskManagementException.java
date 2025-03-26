package com.pp.js.tm.exception;

/**
 * Main exception for all exceptions thrown in tasm management app
 */
public class TaskManagementException extends RuntimeException {

  /**
   * Default constructor.
   *
   * @param message exception message
   */
  public TaskManagementException(String message) {
    super(message);
  }
}
