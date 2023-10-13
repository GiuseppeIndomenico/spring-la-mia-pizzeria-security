package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 128, nullable = false)
	@Length(min = 3, max = 128, message = "Il nome deve essere di almeno tre caratteri")
	private String nome;

	@ManyToMany(mappedBy = "ingredienti")
	private List<Pizza> pizze;

	public Ingrediente() {
	};

	public Ingrediente(String nome, Pizza... pizze) {
		setNome(nome);
		setPizze(Arrays.asList(pizze));

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

}
