package com.wisebits.testapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(fluent = true, chain = true)
@Data
public class BadRequestDto {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("message")
    private List<String> message;
}
