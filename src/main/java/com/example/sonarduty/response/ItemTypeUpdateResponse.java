package com.example.sonarduty.response;

import com.example.sonarduty.model.ItemType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ItemTypeUpdateResponse {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty("error")
    private String error;

    public ItemTypeUpdateResponse(ItemType createdItemType) {
        this.id = createdItemType.getId();
        this.name = createdItemType.getName();
        this.description = createdItemType.getDescription();
    }

    public static ItemTypeUpdateResponse failureResponse(String message) {
        ItemTypeUpdateResponse response = new ItemTypeUpdateResponse();
        response.setError(message);
        return response;
    }
}
