package com.example.sonarduty.model;

import com.example.sonarduty.enums.ItemStatus;
import com.example.sonarduty.request.ItemCreateRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "items")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "item_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "item_type_id")
    private Long itemTypeId;

    @Column(name = "info")
    private String info;

    @Column(name = "location")
    private String location;

    @Column(name = "cost")
    private Long cost;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Basic(optional = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_timestamp", insertable = false, updatable = false)
    private LocalDateTime createdTimestamp;

    @Basic(optional = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_timestamp", insertable = false, updatable = false)
    private LocalDateTime updatedTimestamp;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "purchased_timestamp", insertable = false, updatable = false)
    private LocalDateTime purchasedTimestamp;

    public Item(ItemCreateRequest request) {
        this.itemCode = request.getItemCode();
        this.name = request.getName();
        this.description = request.getDescription();
        this.itemTypeId = request.getItemTypeId();
        this.info = request.getInfo();
        this.location = request.getLocation();
        this.cost = request.getCost();
        if(request.getStatus() == null){
            this.status = ItemStatus.AVAILABLE;
        }
        else{
            this.status = ItemStatus.getStatusEnum(request.getStatus());
        }
        this.purchasedTimestamp = LocalDateTime.parse(request.getPurchasedTimestamp());
    }
}
