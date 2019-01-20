package org.nasa.exploration.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class StringEnumerationValidator implements ConstraintValidator<ValidateStringOptions, String> {

    private Set<String> enumNames;

    private Set<String> extractEnumNames(ValidateStringOptions constraintAnnotation) {
        Class<? extends Enum<?>> enumSelected = constraintAnnotation.enumClass();

        return Arrays.stream(enumSelected.getEnumConstants())
            .map(Enum::name)
            .collect(Collectors.toSet());
    }

    @Override
    public void initialize(ValidateStringOptions constraintAnnotation) {
        enumNames = extractEnumNames(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if ( value == null ) {
            return true;
        }
        return enumNames.contains(value);
    }
}
