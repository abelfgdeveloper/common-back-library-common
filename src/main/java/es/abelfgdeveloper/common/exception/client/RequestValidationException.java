package es.abelfgdeveloper.common.exception.client;

public class RequestValidationException extends BadRequestException {

  private static final long serialVersionUID = -859432492615039774L;

  public RequestValidationException(String message) {
    super(message);
  }
}
