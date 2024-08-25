package com.br.supera.repositories;

import com.br.supera.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    List<ItemModel> findAllByListId(Long listId);
}
