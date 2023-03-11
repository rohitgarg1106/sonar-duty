package com.example.sonarduty.enums;

import lombok.var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ItemStatus {
    AVAILABLE("Available"),
    ISSUED("Issued"),
    DAMAGED("Damaged"),
    REPAIR_IN_PROGRESS("Repair in progress");

    public static Map<String, ItemStatus> cache = new HashMap<>();

    static {
        for(var itemStatus: ItemStatus.values()){
            cache.put(itemStatus.getStatus(), itemStatus);
        }
    }

    private String status;

    ItemStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static ItemStatus getStatusEnum(String status) {
        return cache.get(status);
    }

    public static List<String> getAllStatusValues(){
        return new ArrayList<>(cache.keySet());
    }

    @Override
    public String toString() {
        return "ItemStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
