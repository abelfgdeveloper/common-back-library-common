package es.abelfgdeveloper.common.exception.server;

import es.abelfgdeveloper.common.exception.CommonException;

public class InternalServerErrorException extends CommonException {

  private static final long serialVersionUID = 6638363817763622296L;

  private static final int STATUS = 500;

  protected InternalServerErrorException(String message) {
    super(STATUS, message);
  }

  protected InternalServerErrorException(String message, Throwable cause) {
    super(STATUS, message, cause);
  }
}
