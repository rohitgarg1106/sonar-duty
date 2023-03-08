package com.example.sonarduty.response;

import com.example.sonarduty.model.ItemType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ItemTypeCreateResponse {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty("error")
    private String error;

    public ItemTypeCreateResponse(ItemType createdItemType) {
        this.id = createdItemType.getId();
        this.name = createdItemType.getName();
        this.description = createdItemType.getDescription();
    }

    public static ItemTypeCreateResponse failureResponse(String message) {
        ItemTypeCreateResponse response = new ItemTypeCreateResponse();
        response.setError(message);
        return response;
    }
}
