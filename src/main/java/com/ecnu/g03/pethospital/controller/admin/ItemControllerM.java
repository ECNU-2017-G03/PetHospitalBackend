package com.ecnu.g03.pethospital.controller.admin;

import com.azure.core.annotation.Get;
import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.item.ItemNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.item.ItemDeleteResponse;
import com.ecnu.g03.pethospital.dto.admin.response.item.ItemGetAllResponse;
import com.ecnu.g03.pethospital.dto.admin.response.item.ItemNewResponse;
import com.ecnu.g03.pethospital.dto.admin.response.item.ItemSearchResponse;
import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import com.ecnu.g03.pethospital.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xueying Li, Shen Lei
 * @date 2021/4/14 13:35
 */
@RestController
@RequestMapping(value = "/admin/item", produces = "application/json; charset=UTF-8")
public class ItemControllerM {

    private final ItemService itemService;

    @Autowired
    ItemControllerM(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public ItemGetAllResponse getAll() {
        ItemGetAllResponse response = new ItemGetAllResponse();
        List<ItemEntity> itemEntityList = itemService.getAllItems();
        if (itemEntityList.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setItems(itemEntityList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/delete/{id}")
    public ItemDeleteResponse deleteById(@PathVariable("id") String id) {
        ItemDeleteResponse response = new ItemDeleteResponse();
        if (!itemService.deleteById(id)) {
            response.setSuccessful(false);
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setSuccessful(true);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @PostMapping("/new")
    public ItemNewResponse insert(@RequestBody ItemNewRequest request) {
        ItemNewResponse response = new ItemNewResponse();
        ItemEntity entity = itemService.insert(request.getDescription(), request.getName(), request.getPrice(),
                request.getTime());
        if (entity == null) {
            response.setMessage("Cannot add new item");
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setMessage("Insert item successfully");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setItem(entity);
        return response;
    }

    @GetMapping("/search/{keyword}")
    public ItemSearchResponse searchById(@PathVariable("id") String id) {
        ItemSearchResponse response = new ItemSearchResponse();
        List<ItemEntity> items = itemService.searchById(id);
        if (items.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setItems(items);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @PostMapping("/update")
    public ItemUpdateResponse updateById(@RequestBody ItemUpdateRequest request) {
        ItemUpdateResponse response = new ItemUpdateResponse();
        ItemEntity item = itemService.updateById(request.getId(), request.getName(), request.getDescription(), request.getPrice(), request.getTime());
        if (item == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setItem(item);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

}
