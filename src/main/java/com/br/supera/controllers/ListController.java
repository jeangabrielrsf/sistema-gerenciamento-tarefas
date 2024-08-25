package com.br.supera.controllers;

import com.br.supera.dtos.ItemDTO;
import com.br.supera.dtos.ListDTO;
import com.br.supera.models.ListModel;
import com.br.supera.services.ListService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> getLists(){
        List<ListModel> lists = listService.getAllLists();
        return ResponseEntity.ok(lists);
    }

    @PostMapping
    public ResponseEntity<Object> createList(@RequestBody @Valid ListDTO listDTO){
        listService.createList(listDTO.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Lista criada com sucesso!");
    }
}
