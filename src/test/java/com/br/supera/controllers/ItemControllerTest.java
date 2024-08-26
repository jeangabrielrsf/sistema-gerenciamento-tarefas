package com.br.supera.controllers;

import com.br.supera.dtos.ItemDTO;
import com.br.supera.models.ItemModel;
import com.br.supera.services.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getItemsFromAList_success() {
        Long listId = 1L;
        List<ItemModel> expectedItems = Arrays.asList(
                new ItemModel(1L, "Item 1", false, false, null),
                new ItemModel(2L, "Item 2", false, true, null)
        );

        when(itemService.getItemsFromList(listId)).thenReturn(expectedItems);

        ResponseEntity<?> response = itemController.getItemsFromAList(listId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedItems, response.getBody());
        verify(itemService).getItemsFromList(listId);
    }

    @Test
    void insertItem_success() {
        Long listId = 1L;
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("New Item");

        ResponseEntity<?> response = itemController.insertItem(listId, itemDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(itemDTO, response.getBody());
        verify(itemService).insertItemIntoList(listId, itemDTO);
    }

    @Test
    void changeItemName_success() {
        Long listId = 1L;
        Long itemId = 1L;
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Updated Item");

        ResponseEntity<?> response = itemController.changeItemName(listId, itemId, itemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(itemDTO, response.getBody());
        verify(itemService).changeItemName(listId, itemId, itemDTO.getName());
    }

    @Test
    void deleteItemById_success() {
        Long listId = 1L;
        Long itemId = 1L;

        ResponseEntity<?> response = itemController.deleteItemById(listId, itemId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item deletado com sucesso!", response.getBody());
        verify(itemService).deleteItem(listId, itemId);
    }

    @Test
    void markItemAsDone_success() {
        Long listId = 1L;
        Long itemId = 1L;

        ResponseEntity<?> response = itemController.markItemAsDone(listId, itemId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item marcado como feito!", response.getBody());
        verify(itemService).updateItemAsDone(listId, itemId);
    }

    @Test
    void markItemAsPriority_success() {
        Long listId = 1L;
        Long itemId = 1L;

        ResponseEntity<?> response = itemController.markItemAsPriority(listId, itemId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Item marcado como prioridade!", response.getBody());
        verify(itemService).updateItemAsPriority(listId, itemId);
    }
}