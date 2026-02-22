package com.user.domain.services;

import com.user.domain.entities.User;
import com.user.domain.exceptions.InvalidBirthDateException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserDomainService {

    private static final int MINIMUM_AGE = 18;

    public Boolean isAgeValid(LocalDate birthDate) {
       final int age = Period.between(birthDate, LocalDate.now()).getYears();
       return age >= MINIMUM_AGE;
    }

    public void validateUser(User user) {
        if (!isAgeValid(user.getBirthDate())) {
            throw new InvalidBirthDateException();
        }
    }
}
