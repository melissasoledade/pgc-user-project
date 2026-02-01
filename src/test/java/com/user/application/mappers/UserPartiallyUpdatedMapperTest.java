package com.user.application.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gravity9.jsonpatch.JsonPatch;
import com.gravity9.jsonpatch.ReplaceOperation;
import com.user.domain.entities.User;
import com.user.fixtures.domain.UserHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserPartiallyUpdatedMapperTest {

    @Autowired
    private UserPartiallyUpdatedMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldApplyPatchAndUpdateOnlyProvidedFields() throws Exception {
        // given
        final User user = UserHelper.defaultUser()
                .cpf("99999999999")
                .birthDate(LocalDate.of(1992, 5, 10))
                .email("ana@gmail.com")
                .build();

        final JsonPatch patch = new JsonPatch(List.of(
                new ReplaceOperation("/cpf", objectMapper.valueToTree("55555555555"))
        ));

        // when
        final User patchedUser = mapper.applyPatchToUser(user, patch);

        // then
        assertEquals("55555555555", patchedUser.getCpf());
        assertEquals("ana@gmail.com", patchedUser.getEmail()); // unchanged
        assertEquals(LocalDate.of(1992, 5, 10), patchedUser.getBirthDate()); // unchanged
    }
}
