package com.br.supera.controllers;

import com.br.supera.dtos.ListDTO;
import com.br.supera.models.ListModel;
import com.br.supera.services.ListService;
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

class ListControllerTest {

    @Mock
    private ListService listService;

    @InjectMocks
    private ListController listController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLists_noFilter_success() {
        List<ListModel> expectedLists = Arrays.asList(
                new ListModel(1L, "List 1"),
                new ListModel(2L, "List 2")
        );

        when(listService.getAllLists()).thenReturn(expectedLists);

        ResponseEntity<?> response = listController.getLists(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLists, response.getBody());
        verify(listService).getAllLists();
        verify(listService, never()).getAllListsFiltered(anyString());
    }

    @Test
    void getLists_withFilter_success() {
        String filter = "Test";
        List<ListModel> expectedLists = Arrays.asList(
                new ListModel(1L, "Test List 1"),
                new ListModel(2L, "Test List 2")
        );

        when(listService.getAllListsFiltered(filter)).thenReturn(expectedLists);

        ResponseEntity<?> response = listController.getLists(filter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLists, response.getBody());
        verify(listService).getAllListsFiltered(filter);
        verify(listService, never()).getAllLists();
    }

    @Test
    void createList_success() {
        ListDTO listDTO = new ListDTO();
        listDTO.setName("New List");

        ResponseEntity<Object> response = listController.createList(listDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Lista criada com sucesso!", response.getBody());
        verify(listService).createList(listDTO.getName());
    }
}