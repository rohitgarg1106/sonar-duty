package com.example.sonarduty.request;

import com.example.sonarduty.enums.ItemStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequest {

    @JsonProperty(value = "item_code")
    private String itemCode;

    @JsonProperty(value = "item_name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "item_type")
    private Long itemTypeId;

    @JsonProperty(value = "info")
    private String info;

    @JsonProperty(value = "location")
    private String location;

    @JsonProperty(value = "cost")
    private Long cost;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "purchased_timestamp")
    private String purchasedTimestamp;

}
