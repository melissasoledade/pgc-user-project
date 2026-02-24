package com.user.application.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gravity9.jsonpatch.JsonPatchException;
import com.gravity9.jsonpatch.Patch;
import com.user.domain.entities.User;
import com.user.domain.services.UserDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Component
public class UserPartiallyUpdatedMapper {

    private final ObjectMapper objectMapper;
    private final UserDomainService userDomainService;

    private JsonNode validatePhoneNumberFromNode(JsonNode patchedNode, String phoneNumber) {
        final String validatedPhoneNumber = userDomainService.validatePhoneNumber(phoneNumber);
        return ((ObjectNode) patchedNode).put("phoneNumber", validatedPhoneNumber);
    }

    public User applyPatchToUser(User user, Patch patch) throws JsonProcessingException, JsonPatchException {
        final JsonNode userNode = objectMapper.convertValue(user, JsonNode.class);
        final JsonNode patchedNode = patch.apply(userNode);

        final String phoneNumber = Optional.ofNullable(patchedNode.get("phoneNumber"))
                        .map(JsonNode::asText)
                        .orElse(null);

        if (Objects.nonNull(phoneNumber)) {
            final JsonNode validatedNode = validatePhoneNumberFromNode(patchedNode, phoneNumber);
            return objectMapper.treeToValue(validatedNode, User.class);
        }

        return objectMapper.treeToValue(patchedNode, User.class);
    }
}
