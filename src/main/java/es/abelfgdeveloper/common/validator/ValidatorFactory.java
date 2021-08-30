package es.abelfgdeveloper.common.validator;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidatorFactory {

  private final List<RequestValidator> requestValidators;

  public Optional<RequestValidator> getInstance(String className) {
    RequestValidator response = null;
    for (RequestValidator requestValidator : requestValidators) {
      if (requestValidator.getType().equals(className)) {
        response = requestValidator;
      }
    }
    return Optional.ofNullable(response);
  }
}
