package es.abelfgdeveloper.common.aspect;

import es.abelfgdeveloper.common.validator.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
public class RequestValidatorAspect {

  private final ValidatorFactory validatorFactory;

  @Before("execution(* es.abelfgdeveloper..*Controller.*(..))")
  public void beforeAdvice(JoinPoint joinPoint) {
    List<Object> arguments = Arrays.asList(joinPoint.getArgs());
    for (Object argument : arguments) {
      if (argument != null) {
        validatorFactory
            .getInstance(argument.getClass().getCanonicalName())
            .ifPresent(validator -> validator.validate(argument));
      }
    }
  }
}
