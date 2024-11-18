package spring.jpa.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import spring.jpa.model.Produit;
import spring.jpa.repository.ProduitRepository;
@RestController
@RequestMapping(value="/produits")
 
public class ProduitRESTController {

	@Autowired // pour l'injection de dépendances
	private ProduitRepository produitRepos;
	// Message d'accueil
	// http://localhost:8080/produits/index (GET)
	@Secured(value = {"ROLE_ADMIN","ROLE_USER"})
	@GetMapping(value ="/index" )
	
	public String accueil() {
	return "BienVenue au service Web REST 'produits'.....";
	}
	// Afficher la liste des produits
	// http://localhost:8080/produits/ (GET)
	@Secured(value = {"ROLE_ADMIN","ROLE_USER"})
	@GetMapping(

	// spécifier le path de la méthode
	value= "/",
	// spécifier le format de retour en XML


		produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})

	public List<Produit> getAllProduits() {
	return produitRepos.findAll();
	}
	
	// Afficher un produit en spécifiant son 'id'
	// http://localhost:8080/produits/{id} (GET)
	@Secured(value = {"ROLE_ADMIN","ROLE_USER"})
	@GetMapping(

	// spécifier le path de la méthode qui englobe un paramètre
	value= "/{id}" ,
	// spécifier le format de retour en XML
	produces = MediaType.APPLICATION_XML_VALUE
	)
	public Produit getProduit(@PathVariable Long id) {
	Produit p =produitRepos.findById(id).get();
	return p;
	}
	// Supprimer un produit par 'id' avec la méthode 'GET'
	// http://localhost:8080/produits/delete/{id} (GET)
	@Secured(value = {"ROLE_ADMIN"})
	@GetMapping(

	// spécifier le path de la méthode
	value = "/delete/{id}")

	public void deleteProduit(@PathVariable Long id)
	{
	produitRepos.deleteById(id);

	}
	// ajouter un produit avec la méthode "POST"
	// http://localhost:8080/produits/ (POST)
	@Secured(value = {"ROLE_ADMIN"})
	@PostMapping(

	// spécifier le path de la méthode
	value = "/" ,
	//spécifier le format de retour
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },consumes= { MediaType.APPLICATION_JSON_VALUE }

	)

	public Produit saveProduit(@RequestBody Produit p)
	{
	return produitRepos.save(p);
	}
	// modifier un produit avec la méthode "PUT"
	// http://localhost:8080/produits/ (PUT)
	@Secured(value = {"ROLE_ADMIN"})
	@PutMapping(
	// spécifier le path de la méthode
	value = "/" ,
	//spécifier le format de retour
	produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
	)
	public Produit updateProduit(@RequestBody Produit p)
	{

	return produitRepos.save(p);
	}
	// Supprimer un produit avec la méthode 'DELETE'
	// http://localhost:8080/produits/ (DELETE)
	@Secured(value = {"ROLE_ADMIN"})
	@DeleteMapping(
	// spécifier le path de la méthode
	value = "/")
	public void deleteProduit(@RequestBody Produit p)
	{
	produitRepos.delete(p);
	}
	@RequestMapping(value="getInfosUser",

			produces = {MediaType.APPLICATION_JSON_VALUE })
	Map<String,Object> getInformationsUtilisateurCourant(HttpServletRequest
			request)
			{
			//préparer un objet HashMap
			Map<String,Object> m = new HashMap<String, Object>();
			//Récupérer la session
			HttpSession session =request.getSession();
			//Récupérer le contexte
			SecurityContext ctx =(SecurityContext)
			session.getAttribute("SPRING_SECURITY_CONTEXT");
			//Récupérer le nom de l'utilisateur courant
			String username=ctx.getAuthentication().getName();

			//Préparer une liste pour récupérer les noms des rôles de l'utilisateur courant

			List<String> roles= new ArrayList<String>();
			for (GrantedAuthority ga: ctx.getAuthentication().getAuthorities())
			{
			roles.add(ga.getAuthority());
			}
			//stocker le nom de l'utilisateur
			m.put("username", username);
			//stocker les roles
			m.put("roles",roles);
			return m;
			}
	
}
