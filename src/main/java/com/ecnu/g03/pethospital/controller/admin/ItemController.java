package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.item.ItemNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.item.ItemDeleteResponse;
import com.ecnu.g03.pethospital.dto.admin.response.item.ItemGetAllResponse;
import com.ecnu.g03.pethospital.dto.admin.response.item.ItemNewResponse;
import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import com.ecnu.g03.pethospital.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Body Check Up Item
 * @author Shen Lei
 * @date 2021/4/7 13:08
 */
@RestController
@RequestMapping(value = "/admin/item", produces = "application/json; charset=UTF-8")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/all")
    public ItemGetAllResponse getAll() {
        ItemGetAllResponse response = new ItemGetAllResponse();
        List<ItemEntity> items = itemService.getAllItems();
        if (items.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setItems(items);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @PostMapping("/new")
    public ItemNewResponse insertItem(@RequestBody ItemNewRequest request) {
        ItemNewResponse response = new ItemNewResponse();
        ItemEntity item = itemService.insert(request.getDescription(), request.getName(), request.getPrice(), request.getTime());
        if (item == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setItem(item);
        return response;
    }

    @GetMapping("/delete/{id}")
    public ItemDeleteResponse deleteItem(@PathVariable("id") String id) {
        ItemDeleteResponse response = new ItemDeleteResponse();
        if (!itemService.deleteById(id)) {
            response.setSuccessful(false);
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setSuccessful(true);
        return response;
    }

}
