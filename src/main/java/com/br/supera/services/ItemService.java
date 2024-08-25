package com.br.supera.services;

import com.br.supera.dtos.ItemDTO;
import com.br.supera.exceptions.ListNotFoundException;
import com.br.supera.models.ItemModel;
import com.br.supera.models.ListModel;
import com.br.supera.repositories.ItemRepository;
import com.br.supera.repositories.ListRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ItemService {
    private final ItemRepository itemRepository;
    private final ListRepository listRepository;

    public List<ItemModel> getItemsFromList(Long listId) {
        if (listRepository.findById(listId).isEmpty()) {
            throw new ListNotFoundException();
        }
        return itemRepository.findAllByListId(listId);
    }

    public void insertItemIntoList(Long listId, ItemDTO itemDTO) {
        ListModel list = listRepository.findById(listId).orElseThrow(ListNotFoundException::new);

        ItemModel newItem = new ItemModel();
        newItem.setName(itemDTO.getName());
        newItem.setList(list);
        itemRepository.save(newItem);

    }
}
