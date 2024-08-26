package com.br.supera.repositories;

import com.br.supera.models.ListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<ListModel, Long> {
    List<ListModel> findAllByNameContaining(String name);
}
