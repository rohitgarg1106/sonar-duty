package com.example.sonarduty.request;

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
public class ItemUpdateRequest {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "item_code")
    private String itemCode;

    @JsonProperty(value = "item_name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "info")
    private String info;

    @JsonProperty(value = "location")
    private String location;

    @JsonProperty(value = "cost")
    private Long cost;

    @JsonProperty(value = "status")
    private String status;

}
