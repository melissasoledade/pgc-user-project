package com.user.domain.services;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.user.domain.entities.User;
import com.user.domain.exceptions.InvalidBirthDateException;
import com.user.domain.exceptions.InvalidPhoneNumberException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserDomainService {

    private static final int MINIMUM_AGE = 18;
    private static final String DEFAULT_COUNTRY_CODE = "BR";

    public Boolean isAgeValid(User user) {
       final Optional<LocalDate> birthDate = Optional.ofNullable(user.getBirthDate());

       if (birthDate.isEmpty()) {
           return Boolean.FALSE;
       }

       final int age = Period.between(birthDate.get(), LocalDate.now()).getYears();
       return age >= MINIMUM_AGE;
    }

    public String validatePhoneNumber(String phoneNumberStr) {
        final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
            final PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberStr, DEFAULT_COUNTRY_CODE);

            if (!phoneUtil.isValidNumber(phoneNumber)) {
                throw new InvalidPhoneNumberException();
            }

            return phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            throw new InvalidPhoneNumberException();
        }
    }

    public User validateUser(User user) {
        if (!isAgeValid(user)) {
            throw new InvalidBirthDateException();
        }

        final String validPhoneNumber = validatePhoneNumber(user.getPhoneNumber());
        user.setPhoneNumber(validPhoneNumber);
        return user;
    }
}
