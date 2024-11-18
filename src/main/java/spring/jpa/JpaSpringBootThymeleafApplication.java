package spring.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import spring.jpa.model.Categorie;
import spring.jpa.model.Produit;
import spring.jpa.repository.CategorieRepository;
import spring.jpa.repository.ProduitRepository;

@SpringBootApplication
public class JpaSpringBootThymeleafApplication {

      // Déclarer une référence de l'interface " ProduitRepository"
	static ProduitRepository produitRepos ;

	// Déclarer une référence de l'interface "CategorieRepository"
	static CategorieRepository categorieRepos;

	public static void main(String[] args) {

		// référencer le contexte
		ApplicationContext contexte = SpringApplication.run(JpaSpringBootThymeleafApplication.class, args);	
// Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance
		produitRepos =contexte.getBean(ProduitRepository.class);
		
// Récupérer une implémentation de l'interface "CategorieRepository" par injection de dépendance
		categorieRepos =contexte.getBean(CategorieRepository.class);
		

		// créer deux catégories;
		Categorie cat1 = new Categorie("AL", "Alimentaire");
		Categorie cat2 = new Categorie("PL", "Plastique");	
		
		//Attacher les deux catégories à la BD (insertion)
		categorieRepos.save(cat1);
		categorieRepos.save(cat2);
	

		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		java.util.Date date3 = null;


		try {
			date1 = sdf.parse("2022-04-15");
			date2 = sdf.parse("2022-02-15");
			date3 = sdf.parse("2022-05-15");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();    
		}

		// Insérer 3 produits
		Produit p1 =new Produit("Yahourt", 0.400, 20, date1 , cat1);
		Produit p2 =new Produit("Chocolat", 2000.0, 5, date2, cat1);
		Produit p3 =new Produit("Panier", 1.200, 30, date3, cat2);
		produitRepos.save(p1);
		produitRepos.save(p2);
		produitRepos.save(p3);

		// Insérer une nouvelle  catégorie  avec l'ajout d'un nouveu produit
		 Produit p4 =new Produit("Stylo", 0.400, 20, date1 );
		 Categorie cat3 = new Categorie("BR", "BUREAUTIQUE");
		 p4.setCategorie(cat3);
		 cat3.getProduits().add(p4);
		 categorieRepos.save(cat3);
		 
		//Afficher la liste des produits
		 afficherTousLesProduits(); 
		 
	}

	static void afficherTousLesProduits()
	{
		System.out.println("***************************************");
		// Lister l'ensemble des produits
		System.out.println("Afficher tous les produits...");
           System.out.println("***************************************");
		List<Produit> lp = produitRepos.findAll();
		for (Produit p : lp) 
		{
			System.out.println(p);
		}
		System.out.println("***************************************");
	}
}
