package com.br.supera.controllers;

import com.br.supera.dtos.ItemDTO;
import com.br.supera.dtos.ListDTO;
import com.br.supera.models.ListModel;
import com.br.supera.services.ListService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping(path = "/lists")
public class ListController {

    private final ListService listService;

    @GetMapping
    public ResponseEntity<?> getLists(@RequestParam(required = false) String name){
        if (name != null && !name.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(listService.getAllListsFiltered(name));
        } else {
            List<ListModel> lists = listService.getAllLists();
            return ResponseEntity.status(HttpStatus.OK).body(lists);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createList(@RequestBody @Valid ListDTO listDTO){
        listService.createList(listDTO.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Lista criada com sucesso!");
    }
}
