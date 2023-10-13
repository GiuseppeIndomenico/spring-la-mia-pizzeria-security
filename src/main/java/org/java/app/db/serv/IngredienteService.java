package org.java.app.db.serv;

import java.util.List;
import org.java.app.db.pojo.Ingrediente;
import org.java.app.db.repo.IngredienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepo ingredienteRepo;
	
	public List<Ingrediente> findAll() {
		
		return ingredienteRepo.findAll();
	}
	public Ingrediente findById(int id) {
		
		return ingredienteRepo.findById(id).get();
	}
	public void save(Ingrediente ingrediente) {
		
		ingredienteRepo.save(ingrediente);
	}
	
	public void deleteIngrediente(Ingrediente ingrediente) {
		ingredienteRepo.delete(ingrediente);
	}
	
}
