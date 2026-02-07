package com.user.application.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gravity9.jsonpatch.JsonPatchException;
import com.gravity9.jsonpatch.Patch;
import com.user.domain.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserPartiallyUpdatedMapper {

    private final ObjectMapper objectMapper;

    public User applyPatchToUser(User user, Patch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode userNode = objectMapper.convertValue(user, JsonNode.class);
        JsonNode patchedNode = patch.apply(userNode);
        return objectMapper.treeToValue(patchedNode, User.class);
    }
}
