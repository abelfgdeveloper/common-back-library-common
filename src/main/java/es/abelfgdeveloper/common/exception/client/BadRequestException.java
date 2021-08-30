package es.abelfgdeveloper.common.exception.client;

import es.abelfgdeveloper.common.exception.CommonException;

public class BadRequestException extends CommonException {

  private static final long serialVersionUID = 3434761623540218237L;

  private static final int STATUS = 400;

  protected BadRequestException(String message) {
    super(STATUS, message);
  }

  protected BadRequestException(String message, Throwable cause) {
    super(STATUS, message, cause);
  }
}
