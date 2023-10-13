package org.java.app.controller;

import java.util.List;

import org.java.app.db.pojo.Ingrediente;
import org.java.app.db.pojo.OffertaSpeciale;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.IngredienteService;
import org.java.app.db.serv.OffertaSpecialeService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OffertaSpecialeService offertaSpecialeService;

	@Autowired
	private IngredienteService ingredienteService;

	@GetMapping
	public String getIndex(Model model) {

		List<Pizza> pizze = pizzaService.findAll();

		if (pizze.size() == 0) {
			return "pizza-nothing";
		}

		model.addAttribute("pizze", pizze);

		return "pizza-index";
	}

	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model) {
		Pizza pizza = pizzaService.findById(id);
		if (pizza == null) {
			return "pizza-nothing";
		}

		OffertaSpeciale offertaSpeciale = offertaSpecialeService.findByPizza(pizza);
		model.addAttribute("offertaSpeciale", offertaSpeciale);

		model.addAttribute("pizza", pizza);
		return "pizza-show";
	}

	@GetMapping("/search")
	public String searchPizzeBynome(@RequestParam(name = "nome") String nome, Model model) {
		List<Pizza> pizze = pizzaService.findPizzeByNome(nome);
		if (pizze.isEmpty()) {
			return "pizza-nothing";
		}
		model.addAttribute("pizze", pizze);
		return "pizza-index";
	}

	@GetMapping("/create")
	public String getCreateForm(Model model) {

		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("pizza", new Pizza());

		return "pizza-create";
	}

	@PostMapping("/create")
	public String storePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {

		System.out.println("New pizza: " + pizza);

		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);

			return "pizza-create";
		} else
			System.out.println("No error");

		try {
			pizzaService.save(pizza);
		} catch (Exception e) {

			// CONSTRAIN VALIDATION (unique)
			System.out.println("Errore constrain: " + e.getClass().getSimpleName());

			return "pizza-create";
		}

		return "redirect:/pizze";
	}

	@GetMapping("/update/{id}")
	public String getUpdateForm(@PathVariable int id, Model model) {
		Pizza pizza = pizzaService.findById(id);
		List<Ingrediente> allIngredienti = ingredienteService.findAll();
		List<Ingrediente> pizzaIngredienti = pizza.getIngredienti();

		model.addAttribute("pizza", pizza);
		model.addAttribute("allIngredienti", allIngredienti);
		model.addAttribute("pizzaIngredienti", pizzaIngredienti);

		return "pizza-update";
	}

	@PostMapping("/update/{id}")
	public String updatePizza(@Valid @ModelAttribute Pizza updatedPizza, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "pizza-update";
		}
		Pizza existingPizza = pizzaService.findById(updatedPizza.getId());

		existingPizza.setNome(updatedPizza.getNome());
		existingPizza.setDescrizione(updatedPizza.getDescrizione());
		existingPizza.setFoto(updatedPizza.getFoto());
		existingPizza.setPrezzo(updatedPizza.getPrezzo());
		existingPizza.setIngredienti(updatedPizza.getIngredienti());

		pizzaService.save(existingPizza);

		return "redirect:/pizze";
	}

	// Offerte

	@GetMapping("/offerta/create")
	public String getOffertaCreateForm(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		model.addAttribute("offertaSpeciale", new OffertaSpeciale());
		return "offerta-create";
	}

	@PostMapping("/offerta/create")
	public String createOffertaSpeciale(@Valid @ModelAttribute OffertaSpeciale offertaSpeciale,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "offerta-create";
		}
		int pizzaId = offertaSpeciale.getPizza().getId();
		Pizza pizza = pizzaService.findById(pizzaId);
		offertaSpeciale.setPizza(pizza);
		offertaSpecialeService.save(offertaSpeciale);

		return "redirect:/pizze/" + pizzaId;
	}

	@GetMapping("/offerta/update/{offertaId}")
	public String getOffertaUpdateForm(@PathVariable int offertaId, Model model) {
		OffertaSpeciale offertaSpeciale = offertaSpecialeService.findById(offertaId);

		model.addAttribute("offertaSpeciale", offertaSpeciale);

		return "offerta-update";
	}

	@PostMapping("/offerta/update/{offertaId}")
	public String updateOffertaSpeciale(@PathVariable int offertaId,
			@Valid @ModelAttribute OffertaSpeciale offertaSpeciale, @RequestParam("pizzaId") int pizzaId,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "offerta-update";
		}

		Pizza pizza = pizzaService.findById(pizzaId);
		offertaSpeciale.setPizza(pizza);

		offertaSpeciale.setId(offertaId);
		offertaSpecialeService.save(offertaSpeciale);

		return "redirect:/pizze/" + pizzaId;
	}

}