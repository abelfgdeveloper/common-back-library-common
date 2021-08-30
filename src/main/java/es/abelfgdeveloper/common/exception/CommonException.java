package es.abelfgdeveloper.common.exception;

import lombok.Getter;

public class CommonException extends RuntimeException {

  private static final long serialVersionUID = -2962027671639502047L;

  @Getter private final int status;

  protected CommonException(int status, String message) {
    super(message);
    this.status = status;
  }

  protected CommonException(int status, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
  }
}
