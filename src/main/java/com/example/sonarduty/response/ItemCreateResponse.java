package com.example.sonarduty.response;

import com.example.sonarduty.enums.ItemStatus;
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
public class ItemCreateResponse {
    
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "item_code")
    private String itemCode;

    @JsonProperty(value = "item_name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "item_type_id")
    private Long itemTypeId;

    @JsonProperty(value = "info")
    private String info;

    @JsonProperty(value = "location")
    private String location;

    @JsonProperty(value = "cost")
    private Long cost;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "created_timestamp")
    private String createdTimestamp;

    @JsonProperty(value = "updated_timestamp")
    private String updatedTimestamp;

    @JsonProperty(value = "purchased_timestamp")
    private String purchasedTimestamp;

    @JsonProperty("error")
    private String error;
    public static ItemCreateResponse failureResponse(String message) {
        ItemCreateResponse response = new ItemCreateResponse();
        response.setError(message);
        return response;
    }
}
