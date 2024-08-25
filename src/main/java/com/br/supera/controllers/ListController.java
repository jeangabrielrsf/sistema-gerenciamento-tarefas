package com.br.supera.controllers;

import com.br.supera.models.ListModel;
import com.br.supera.services.ListService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
