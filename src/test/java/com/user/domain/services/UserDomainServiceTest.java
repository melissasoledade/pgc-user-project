package com.user.domain.services;

import com.user.domain.entities.User;
import com.user.domain.exceptions.InvalidBirthDateException;
import com.user.domain.exceptions.InvalidPhoneNumberException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserDomainServiceTest {

    @Autowired
    private UserDomainService userDomainService;

    @Test
    void shouldReturnTrueWhenUserIsAtLeast18YearsOld() {
        // given
        final User user = User.builder()
                .birthDate(LocalDate.now().minusYears(20))
                .build();

        // when
        final Boolean result = userDomainService.isAgeValid(user);

        // then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenUserIsUnder18() {
        // given
        final User user = User.builder()
                .birthDate(LocalDate.now().minusYears(17))
                .build();

        // when
        final Boolean result = userDomainService.isAgeValid(user);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenBirthDateIsNull() {
        // given
        final User user = User.builder()
                .birthDate(null)
                .build();

        // when
        final Boolean result = userDomainService.isAgeValid(user);

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnFormattedPhoneNumberWhenValid() {
        // given and when
        final String phone1 = userDomainService.validatePhoneNumber("+5511988888888");
        final String phone2 = userDomainService.validatePhoneNumber("11988888888");

        // then
        assertEquals("+5511988888888", phone1);
        assertEquals("+5511988888888", phone2);
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberIsInvalid() {
        // given
        final String invalidPhone = "+11988888888";
        final String invalidPhone2 = "551188888888";

        // when and then
        assertThrows(
                InvalidPhoneNumberException.class,
                () -> userDomainService.validatePhoneNumber(invalidPhone)
        );

        assertThrows(
                InvalidPhoneNumberException.class,
                () -> userDomainService.validatePhoneNumber(invalidPhone2)
        );
    }

    @Test
    void shouldValidateUserSuccessfully() {
        // given

        final User user = User.builder()
                .birthDate(LocalDate.now().minusYears(25))
                .phoneNumber("11987654321")
                .build();

        // when
        final User validatedUser = userDomainService.validateUser(user);

        // then
        assertEquals("+5511987654321", validatedUser.getPhoneNumber());
        assertEquals(LocalDate.now().minusYears(25), validatedUser.getBirthDate());
    }

    @Test
    void shouldThrowExceptionWhenUserIsUnderage() {
        // given
        User user = User.builder()
                .birthDate(LocalDate.now().minusYears(15))
                .phoneNumber("11987654321")
                .build();

        // when and then
        assertThrows(
                InvalidBirthDateException.class,
                () -> userDomainService.validateUser(user)
        );
    }

    @Test
    void shouldThrowExceptionWhenPhoneIsInvalid() {
        // given
        final User user = User.builder()
                .birthDate(LocalDate.now().minusYears(25))
                .phoneNumber("+55119")
                .build();

        // when and then
        assertThrows(
                InvalidPhoneNumberException.class,
                () -> userDomainService.validateUser(user)
        );
    }
}
