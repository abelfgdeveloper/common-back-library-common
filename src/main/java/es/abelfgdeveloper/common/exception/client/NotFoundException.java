package es.abelfgdeveloper.common.exception.client;

import es.abelfgdeveloper.common.exception.CommonException;

public class NotFoundException extends CommonException {

  private static final long serialVersionUID = -6476477729035784763L;

  private static final int STATUS = 404;

  protected NotFoundException(String message) {
    super(STATUS, message);
  }

  protected NotFoundException(String message, Throwable cause) {
    super(STATUS, message, cause);
  }
}
