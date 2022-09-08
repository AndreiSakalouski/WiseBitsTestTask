package com.wisebits.testapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Data
public class UserCreatedDto {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("details")
    private UserDetailsDto userDetails;
    @JsonProperty("message")
    private String message;
}
