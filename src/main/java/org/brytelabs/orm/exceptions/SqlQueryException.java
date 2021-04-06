package org.brytelabs.orm.exceptions;

public class SqlQueryException extends RuntimeException {
  public SqlQueryException(String message) {
    super(message);
  }
}
