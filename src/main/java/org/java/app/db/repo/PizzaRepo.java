package org.java.app.db.repo;

import org.java.app.db.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {
    List<Pizza> findByNomeContaining(String nome);
}
