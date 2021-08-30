package es.abelfgdeveloper.common.exception.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.abelfgdeveloper.common.dto.error.ErrorResponseResource;
import es.abelfgdeveloper.common.exception.CommonException;
import es.abelfgdeveloper.common.exception.mapper.StackTraceMapper;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class CommonResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String SLASH = "-";

  private final StackTraceMapper stackTraceMapper;
  private final ObjectMapper objectMapper;

  @Value("${abelfgdeveloper.error.service-error}")
  private String serviceError;

  @ExceptionHandler(HttpClientErrorException.class)
  protected ResponseEntity<Object> handleHttpClientErrorException(
      HttpClientErrorException exception) {
    ErrorResponseResource body = null;
    try {
      body =
          objectMapper.readValue(exception.getResponseBodyAsString(), ErrorResponseResource.class);
    } catch (Exception e) {
      body = buildErrorResponseResource(exception, "000-000-000");
    }
    return buildResponse(body, HttpStatus.resolve(exception.getRawStatusCode()));
  }

  @ExceptionHandler(CommonException.class)
  protected ResponseEntity<Object> handleCommonException(
      CommonException exception, WebRequest request) {
    String message =
        new StringBuilder()
            .append(serviceError)
            .append(SLASH)
            .append(exception.getMessage())
            .toString();
    ErrorResponseResource body = buildErrorResponseResource(exception, message);
    return buildResponse(body, HttpStatus.resolve(exception.getStatus()));
  }

  private ResponseEntity<Object> buildResponse(ErrorResponseResource body, HttpStatus httpStatus) {
    return new ResponseEntity<>(body, httpStatus);
  }

  private ErrorResponseResource buildErrorResponseResource(Exception exception, String message) {
    return ErrorResponseResource.builder()
        .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
        .code(message)
        .detail(exception.getClass().getSimpleName())
        .stackTrace(stackTraceMapper.map(exception.getStackTrace()))
        .cause(null)
        .build();
  }
}
