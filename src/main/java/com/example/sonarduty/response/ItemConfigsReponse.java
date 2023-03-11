package com.example.sonarduty.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemConfigsReponse {

    @JsonProperty("status")
    private List<String> statusList;

    @JsonProperty("item_type")
    private List<Map<String,Long>> itemTypeList;

    @JsonProperty("error")
    private String error;

    public static ItemConfigsReponse failureResponse(String message) {
        ItemConfigsReponse response = new ItemConfigsReponse();
        response.setError(message);
        return response;
    }

}
