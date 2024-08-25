package com.br.supera.services;

import com.br.supera.models.ListModel;
import com.br.supera.repositories.ListRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ListService {
    private final ListRepository listRepository;

    public List<ListModel> getAllLists() {
        List<ListModel> lists = listRepository.findAll();
        return lists;
    }

    public void createList(String name) {
        ListModel list = new ListModel();
        list.setName(name);
        listRepository.save(list);
    }
}
