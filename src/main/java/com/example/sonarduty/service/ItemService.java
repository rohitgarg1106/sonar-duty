package com.example.sonarduty.service;

import com.example.sonarduty.enums.ItemStatus;
import com.example.sonarduty.exception.BadRequestException;
import com.example.sonarduty.model.Item;
import com.example.sonarduty.model.ItemChangeLog;
import com.example.sonarduty.model.ItemType;
import com.example.sonarduty.repository.ItemChangeLogRepository;
import com.example.sonarduty.repository.ItemRepository;
import com.example.sonarduty.repository.ItemTypeRepository;
import com.example.sonarduty.request.ItemCreateRequest;
import com.example.sonarduty.request.ItemUpdateRequest;
import com.example.sonarduty.response.ItemConfigsReponse;
import com.example.sonarduty.response.ItemCreateResponse;
import com.example.sonarduty.response.ItemUpdateResponse;
import com.example.sonarduty.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemService {

    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public ItemTypeRepository itemTypeRepository;

    @Autowired
    public ItemChangeLogRepository itemChangeLogRepository;

    public ItemCreateResponse createItem(ItemCreateRequest request) {
        validateCreateItemRequestParams(request);
        Item item = new Item(request);
        Item createdItem = itemRepository.save(item);
        return createItemCreateResponse(createdItem);
    }

    public ItemCreateResponse createItemCreateResponse(Item createdItem) {
        ItemCreateResponse itemCreateResponse = new ItemCreateResponse();
        itemCreateResponse.setCreatedTimestamp(CommonUtils.convertLocalDateTimeToString(createdItem.getCreatedTimestamp()));
        itemCreateResponse.setUpdatedTimestamp(CommonUtils.convertLocalDateTimeToString(createdItem.getUpdatedTimestamp()));
        itemCreateResponse.setPurchasedTimestamp(CommonUtils.convertLocalDateTimeToString(createdItem.getPurchasedTimestamp()));
        itemCreateResponse.setId(createdItem.getId());
        itemCreateResponse.setItemCode(createdItem.getItemCode());
        itemCreateResponse.setItemTypeId(createdItem.getItemTypeId());
        itemCreateResponse.setDescription(createdItem.getDescription());
        itemCreateResponse.setCost(createdItem.getCost());
        itemCreateResponse.setInfo(createdItem.getInfo());
        itemCreateResponse.setLocation(createdItem.getLocation());
        itemCreateResponse.setStatus(createdItem.getStatus()!= null?createdItem.getStatus().getStatus():null);
        return itemCreateResponse;
    }

    public void validateCreateItemRequestParams(ItemCreateRequest request) {
        // todo{rohit.garg}
    }

    public Item getItem(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new BadRequestException("Item with given id does not exists"));
    }

    public List<Item> getAllItems() {
//        return itemRepository.findAll(Sort.by(Sort.Direction.DESC, field));
        return itemRepository.findAll();
    }

    public ItemUpdateResponse updateItem(ItemUpdateRequest request) {
        Item item = itemRepository.findById(request.getId()).orElseThrow(() -> new BadRequestException("Item with given id does not exists"));
        logOldItemDetails(item);
        item.setItemCode(request.getItemCode());
        item.setCost(request.getCost());
        item.setDescription(request.getDescription());
        item.setInfo(request.getInfo());
        item.setLocation(request.getLocation());
        item.setName(request.getName());
        item.setStatus(ItemStatus.getStatusEnum(request.getStatus()));
        Item updatedItem = itemRepository.saveAndFlush(item);
        return getItemUpdateResponse(updatedItem);
    }

    public ItemUpdateResponse getItemUpdateResponse(Item createdItem) {
        ItemUpdateResponse itemUpdateResponse = new ItemUpdateResponse();
        itemUpdateResponse.setCreatedTimestamp(CommonUtils.convertLocalDateTimeToString(createdItem.getCreatedTimestamp()));
        itemUpdateResponse.setUpdatedTimestamp(CommonUtils.convertLocalDateTimeToString(createdItem.getUpdatedTimestamp()));
        itemUpdateResponse.setPurchasedTimestamp(CommonUtils.convertLocalDateTimeToString(createdItem.getPurchasedTimestamp()));
        itemUpdateResponse.setId(createdItem.getId());
        itemUpdateResponse.setItemCode(createdItem.getItemCode());
        itemUpdateResponse.setItemTypeId(createdItem.getItemTypeId());
        itemUpdateResponse.setDescription(createdItem.getDescription());
        itemUpdateResponse.setCost(createdItem.getCost());
        itemUpdateResponse.setInfo(createdItem.getInfo());
        itemUpdateResponse.setLocation(createdItem.getLocation());
        itemUpdateResponse.setStatus(createdItem.getStatus()!= null?createdItem.getStatus().getStatus():null);
        return itemUpdateResponse;
    }
    public void logOldItemDetails(Item item) {
        ItemChangeLog itemChangeLog = new ItemChangeLog();
        itemChangeLog.setItemId(item.getId());
        itemChangeLog.setItemCode(item.getItemCode());
        itemChangeLog.setCost(item.getCost());
        itemChangeLog.setInfo(item.getInfo());
        itemChangeLog.setLocation(item.getLocation());
        itemChangeLog.setName(item.getName());
        itemChangeLog.setDescription(item.getDescription());
        itemChangeLog.setStatus(item.getStatus());
        itemChangeLogRepository.saveAndFlush(itemChangeLog);
    }

    public ItemConfigsReponse getConfigs() {
        List<ItemType> itemTypeList = itemTypeRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        List<Map<String,Long>> itemMapList = itemTypeList.stream().map(i -> Collections.singletonMap(i.getName(), i.getId())).collect(Collectors.toList());
        return ItemConfigsReponse.builder()
                .itemTypeList(itemMapList)
                .statusList(ItemStatus.getAllStatusValues())
                .build();
    }
}
