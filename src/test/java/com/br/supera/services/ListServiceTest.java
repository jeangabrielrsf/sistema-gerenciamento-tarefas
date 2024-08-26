package com.br.supera.services;

import com.br.supera.models.ListModel;
import com.br.supera.repositories.ListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListServiceTest {

    @Mock
    private ListRepository listRepository;

    @InjectMocks
    private ListService listService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLists_success() {
        List<ListModel> expectedLists = Arrays.asList(
                new ListModel(1L, "List 1"),
                new ListModel(2L, "List 2")
        );

        when(listRepository.findAll()).thenReturn(expectedLists);

        List<ListModel> result = listService.getAllLists();

        assertEquals(expectedLists, result);
        verify(listRepository).findAll();
    }

    @Test
    void getAllListsFiltered_success() {
        String name = "Test";
        List<ListModel> expectedLists = Arrays.asList(
                new ListModel(1L, "Test List 1"),
                new ListModel(2L, "Test List 2")
        );

        when(listRepository.findAllByNameContaining(name)).thenReturn(expectedLists);

        List<ListModel> result = listService.getAllListsFiltered(name);

        assertEquals(expectedLists, result);
        verify(listRepository).findAllByNameContaining(name);
    }

    @Test
    void createList_success() {
        String listName = "New List";
        ListModel expectedList = new ListModel(null, listName);

        listService.createList(listName);

        verify(listRepository).save(argThat(list ->
                list.getName().equals(listName) && list.getId() == null
        ));
    }
}