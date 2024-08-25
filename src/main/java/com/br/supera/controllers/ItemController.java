package com.br.supera.controllers;

import com.br.supera.dtos.ItemDTO;
import com.br.supera.models.ItemModel;
import com.br.supera.services.ItemService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{listId}")
    public ResponseEntity<?> getItemsFromAList(@PathVariable Long listId) {
        List<ItemModel> items = itemService.getItemsFromList(listId);
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @PostMapping("/{listId}")
    public ResponseEntity<?> insertItem(@PathVariable Long listId, @RequestBody ItemDTO itemDTO) {
        itemService.insertItemIntoList(listId, itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemDTO);
    }

    @PutMapping("/{listId}/{itemId}")
    public ResponseEntity<?> changeItemName(@PathVariable Long listId, @PathVariable Long itemId, @RequestBody ItemDTO itemDTO) {
        itemService.changeItemName(listId, itemId, itemDTO.getName());
        return ResponseEntity.status(HttpStatus.OK).body(itemDTO);
    }
}
