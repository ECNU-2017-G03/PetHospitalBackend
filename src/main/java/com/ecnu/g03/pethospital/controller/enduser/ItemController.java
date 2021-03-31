package com.ecnu.g03.pethospital.controller.enduser;

import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import com.ecnu.g03.pethospital.service.ItemService;
import com.ecnu.g03.pethospital.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-30 12:42
 */
@RestController
@RequestMapping("/user/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * @return department name list
     */
    @GetMapping("/list")
    @JwtToken
    public ResponseEntity<?> getItems() {
        List<ItemEntity> itemList = itemService.getAllItems();
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }
}
