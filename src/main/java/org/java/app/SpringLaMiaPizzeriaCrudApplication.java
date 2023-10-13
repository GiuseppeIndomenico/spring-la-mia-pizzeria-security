package org.java.app;

import org.java.app.db.pojo.Ingrediente;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.IngredienteService;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {
	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private IngredienteService ingredienteService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingrediente i1 = new Ingrediente("mozzarella");
		Ingrediente i2 = new Ingrediente("pomodoro");
		Ingrediente i3 = new Ingrediente("olive");
		Ingrediente i4 = new Ingrediente("melanzane");
		Ingrediente i5 = new Ingrediente("cipolla");
		Ingrediente i6 = new Ingrediente("ricotta salata");
		Ingrediente i7 = new Ingrediente("pistacchio");
		Ingrediente i8 = new Ingrediente("mortadella");
		Ingrediente i9 = new Ingrediente("funghi");
		Ingrediente i10 = new Ingrediente("uova");
		Ingrediente i11 = new Ingrediente("piselli");
		Ingrediente i12 = new Ingrediente("salame piccante");
		Ingrediente i13 = new Ingrediente("olio");
		Ingrediente i14 = new Ingrediente("origano");
		Ingrediente i15 = new Ingrediente("prosciutto cotto");
		
		
		ingredienteService.save(i1);
		ingredienteService.save(i2);
		ingredienteService.save(i3);
		ingredienteService.save(i4);
		ingredienteService.save(i5);
		ingredienteService.save(i6);
		ingredienteService.save(i7);
		ingredienteService.save(i8);
		ingredienteService.save(i9);
		ingredienteService.save(i10);
		ingredienteService.save(i11);
		ingredienteService.save(i12);
		ingredienteService.save(i13);
		ingredienteService.save(i14);
		ingredienteService.save(i15);
	
		
		
		

		Pizza pizza1 = new Pizza("margherita", "La classica",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpZlDgR_rRgGS3XIeDM1OVADNdBx8b9P_kBSHWep0X9L5sPiQlrXlxpOvNskqf5xhAQRg&usqp=CAU",
				4, i1,i2);
		Pizza pizza2 = new Pizza("biancaneve", "formaggiosa",
				"https://www.menupizza.it/cmsportal/common/cache/content_manager/images/1785/500x500/ricetta-pizza-biancaneve-foto.jpg",
				4, i1,i13,i14);
		Pizza pizza3 = new Pizza("norma", "odore di sicilia",
				"https://blog.giallozafferano.it/dolcesalatomiky/wp-content/uploads/2017/07/DSC_0024-3.jpg", 6, i1,i2,i4,i6);
		Pizza pizza4 = new Pizza("capricciosa", "per chi non si accontenta",
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGsaaLUAHMGlAidQtg77kwfMrY2WRET2ikHg&usqp=CAU",
				7, i1,i2,i3,i9,i10,i11,i15);
		Pizza pizza5 = new Pizza("diavola", "tante fiamme!",
				"https://www.negroni.com/sites/negroni.com/files/styles/scale__1440_x_1440_/public/pizza_rustica.jpg?itok=Lbp_jtZW",
				5,i1,i2,i12);
		Pizza pizza6 = new Pizza("bronte", "vero food-porn",
				"https://www.mysocialrecipe.com/files/admin/immagini/pistacchiosa1-master.jpg",
				10, i1,i13,i8,i7);
		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		pizzaService.save(pizza4);
		pizzaService.save(pizza5);
		pizzaService.save(pizza6);

		
		System.out.println("Insert OK!");
	}
}
