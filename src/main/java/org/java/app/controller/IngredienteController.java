package org.java.app.controller;

import java.util.List;

import org.java.app.db.pojo.Ingrediente;
import org.java.app.db.serv.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@GetMapping
	public String getIndex(Model model) {

		List<Ingrediente> ingrediente = ingredienteService.findAll();

		model.addAttribute("ingrediente", ingrediente);

		return "ingrediente-index";
	}

	@GetMapping("/create")
	public String getCreateForm(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "ingrediente-create";
	}

	@PostMapping("/create")
	public String createIngrediente(@Valid @ModelAttribute Ingrediente ingrediente, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
	
			return "ingrediente-create";
		}

		try {

			ingredienteService.save(ingrediente);

			return "redirect:/ingredienti"; 
		} catch (Exception e) {
			
			model.addAttribute("error", "Impossibile creare l'ingrediente.");
			
			return "ingrediente-create"; 
		}
	}

}
