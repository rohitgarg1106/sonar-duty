package com.example.sonarduty.service;

import com.example.sonarduty.exception.BadRequestException;
import com.example.sonarduty.model.Item;
import com.example.sonarduty.model.ItemType;
import com.example.sonarduty.repository.ItemTypeRepository;
import com.example.sonarduty.request.ItemTypeCreateRequest;
import com.example.sonarduty.request.ItemTypeUpdateRequest;
import com.example.sonarduty.response.ItemTypeCreateResponse;
import com.example.sonarduty.response.ItemTypeUpdateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.sonarduty.utils.CommonUtils.isStringEmpty;

@Service
@Slf4j
public class ItemTypeService {

    @Autowired
    private ItemTypeRepository itemTypeRepository;
    public ItemTypeCreateResponse createItemType(ItemTypeCreateRequest request) {
        validateCreateItemTypeRequestParams(request);
        ItemType itemType = new ItemType(request);
        ItemType createdItemType = itemTypeRepository.saveAndFlush(itemType);
        return new ItemTypeCreateResponse(createdItemType);

    }

    public void validateCreateItemTypeRequestParams(ItemTypeCreateRequest request) {
        if(isStringEmpty(request.getDescription())){
            throw new BadRequestException("Item Type Description can not be empty");
        }

        if(isStringEmpty(request.getName())){
            throw new BadRequestException("Item Type Name can not be empty");
        }

        request.setName(request.getName().toUpperCase());

        if(itemTypeRepository.findByName(request.getName()).isPresent()){
            throw new BadRequestException("Item Type with given name already exists");
        }
    }

    public ItemTypeUpdateResponse updateItemType(ItemTypeUpdateRequest request) {
        validateUpdateItemTypeRequestParams(request);
        ItemType itemType = itemTypeRepository.findById(request.getId()).orElseThrow(()-> new BadRequestException("Item type with given id not found"));
        itemType.setId(request.getId());
        itemType.setName(request.getName());
        itemType.setDescription(request.getDescription());
        ItemType createdItemType = itemTypeRepository.saveAndFlush(itemType);
        return new ItemTypeUpdateResponse(createdItemType);
    }

    public void validateUpdateItemTypeRequestParams(ItemTypeUpdateRequest request) {

        if(request.getId() == null){
            throw new BadRequestException("Item Type Id can not be empty");
        }

        if(isStringEmpty(request.getDescription())){
            throw new BadRequestException("Item Type Description can not be empty");
        }

        if(isStringEmpty(request.getName())){
            throw new BadRequestException("Item Type Name can not be empty");
        }

        request.setName(request.getName().toUpperCase());

        if(itemTypeRepository.findByName(request.getName()).isPresent()){
            throw new BadRequestException("Item Type with given name already exists");
        }

    }

    public List<ItemType> getAllTypes(String field) {
        return itemTypeRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    public ItemType getItemTypeById(Long id) {
        return itemTypeRepository.findById(id).orElseThrow(()-> new BadRequestException("Item type with given id not found"));
    }
}
