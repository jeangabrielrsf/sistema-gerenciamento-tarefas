package com.br.supera.services;

import com.br.supera.dtos.ItemDTO;
import com.br.supera.exceptions.ItemNotFoundException;
import com.br.supera.exceptions.ListNotFoundException;
import com.br.supera.models.ItemModel;
import com.br.supera.models.ListModel;
import com.br.supera.repositories.ItemRepository;
import com.br.supera.repositories.ListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ListRepository listRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getItemsFromList_success() {
        Long listId = 1L;
        ListModel list = new ListModel(listId, "Test List");
        List<ItemModel> expectedItems = Arrays.asList(
                new ItemModel(1L, "Item 1", false, false, list),
                new ItemModel(2L, "Item 2", false, true, list)
        );

        when(listRepository.findById(listId)).thenReturn(Optional.of(list));
        when(itemRepository.findAllByListIdOrderByPriority(listId)).thenReturn(expectedItems);

        List<ItemModel> result = itemService.getItemsFromList(listId);

        assertEquals(expectedItems, result);
        verify(listRepository).findById(listId);
        verify(itemRepository).findAllByListIdOrderByPriority(listId);
    }

    @Test
    void getItemsFromList_listNotFound() {
        Long listId = 1L;
        when(listRepository.findById(listId)).thenReturn(Optional.empty());

        assertThrows(ListNotFoundException.class, () -> itemService.getItemsFromList(listId));
        verify(listRepository).findById(listId);
        verify(itemRepository, never()).findAllByListIdOrderByPriority(anyLong());
    }

    @Test
    void insertItemIntoList_success() {
        Long listId = 1L;
        ListModel list = new ListModel(listId, "Test List");
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("New Item");

        when(listRepository.findById(listId)).thenReturn(Optional.of(list));

        itemService.insertItemIntoList(listId, itemDTO);

        verify(listRepository).findById(listId);
        verify(itemRepository).save(any(ItemModel.class));
    }

    @Test
    void insertItemIntoList_listNotFound() {
        Long listId = 1L;
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("New Item");

        when(listRepository.findById(listId)).thenReturn(Optional.empty());

        assertThrows(ListNotFoundException.class, () -> itemService.insertItemIntoList(listId, itemDTO));
        verify(listRepository).findById(listId);
        verify(itemRepository, never()).save(any(ItemModel.class));
    }

    @Test
    void changeItemName_success() {
        Long listId = 1L;
        Long itemId = 1L;
        String newName = "Updated Item";
        ListModel list = new ListModel(listId, "Test List");
        ItemModel item = new ItemModel(itemId, "Old Name", false, false, list);

        when(listRepository.findById(listId)).thenReturn(Optional.of(list));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        itemService.changeItemName(listId, itemId, newName);

        assertEquals(newName, item.getName());
        verify(listRepository).findById(listId);
        verify(itemRepository).findById(itemId);
        verify(itemRepository).save(item);
    }

    @Test
    void changeItemName_listNotFound() {
        Long listId = 1L;
        Long itemId = 1L;
        String newName = "Updated Item";

        when(listRepository.findById(listId)).thenReturn(Optional.empty());

        assertThrows(ListNotFoundException.class, () -> itemService.changeItemName(listId, itemId, newName));
        verify(listRepository).findById(listId);
        verify(itemRepository, never()).findById(anyLong());
        verify(itemRepository, never()).save(any(ItemModel.class));
    }

    @Test
    void changeItemName_itemNotFound() {
        Long listId = 1L;
        Long itemId = 1L;
        String newName = "Updated Item";
        ListModel list = new ListModel(listId, "Test List");

        when(listRepository.findById(listId)).thenReturn(Optional.of(list));
        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> itemService.changeItemName(listId, itemId, newName));
        verify(listRepository).findById(listId);
        verify(itemRepository).findById(itemId);
        verify(itemRepository, never()).save(any(ItemModel.class));
    }
}