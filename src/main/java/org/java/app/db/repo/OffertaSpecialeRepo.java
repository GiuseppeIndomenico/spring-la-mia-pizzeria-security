package org.java.app.db.repo;

import org.java.app.db.pojo.OffertaSpeciale;
import org.java.app.db.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertaSpecialeRepo extends JpaRepository<OffertaSpeciale, Integer> {
	OffertaSpeciale findByPizza(Pizza pizza);
}
