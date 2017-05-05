package com.motorcycle.areas.user.userValidations;

import com.motorcycle.areas.user.models.bindingModels.RegisterUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordsMatchingValidator implements ConstraintValidator<FieldMatch, Object> {

    @Override
    public void initialize(FieldMatch isPasswordsMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if(userClass instanceof RegisterUser){
            return ((RegisterUser) userClass).getPassword().equals(((RegisterUser) userClass).getConfirmPassword());
        }

        return false;
    }
}
