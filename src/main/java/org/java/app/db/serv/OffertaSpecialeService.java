package org.java.app.db.serv;

import java.util.List;
import org.java.app.db.repo.OffertaSpecialeRepo;
import org.java.app.db.pojo.OffertaSpeciale;
import org.java.app.db.pojo.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaSpecialeService {

	@Autowired
	private OffertaSpecialeRepo offertaSpecialeRepo; 

	public List<OffertaSpeciale> findAll() {
		return offertaSpecialeRepo.findAll();
	}

	public OffertaSpeciale findById(int id) {
		return offertaSpecialeRepo.findById(id).get();
	}

	public void save(OffertaSpeciale offertaSpeciale) {
		offertaSpecialeRepo.save(offertaSpeciale);
	}

	public void delete(OffertaSpeciale offertaSpeciale) {
		offertaSpecialeRepo.delete(offertaSpeciale);
	}
	
	public OffertaSpeciale findByPizza(Pizza pizza) {

	    return offertaSpecialeRepo.findByPizza(pizza);
	}
}
