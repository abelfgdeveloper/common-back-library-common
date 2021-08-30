package es.abelfgdeveloper.common.validator;

public interface RequestValidator {

  String getType();

  void validate(Object request);
}
