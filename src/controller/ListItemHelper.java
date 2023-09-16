package controller;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

public class ListItemHelper {
static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("AnimalAtZoo");

public void insertItem(ListItem li) {
	EntityManager em = emfactory.createEntityManager();
	em.getTransaction().begin();
	em.persist(li);	
	em.getTransaction().commit();
	em.close();
	
}
	public List<ListItem> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<ListItem> allItems = em.createQuery("SELECT i FROM ListItem i").getResultList();
		return allItems;
		}
	public void deleteItem(ListItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("SELECT li FROM ListItem li WHERE li.animal = :selectedAnimal AND li.item = :selectedItem", ListItem.class);
		
		typedQuery.setParameter("selectedAnimal", toDelete.getAnimal());
		typedQuery.setParameter("selectedItem",
		toDelete.getItem());
		
		typedQuery.setMaxResults(1);
		
		ListItem result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em
		.close();
		}
	public List<ListItem> searchForItemByStore(String animalName) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.animal = :selectedAnimal", ListItem.class);
		typedQuery.setParameter("selectedAnimal", animalName);
		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		}
	public List<ListItem> searchForItemByItem(String itemName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.item = :selectedItem", ListItem.class);
		typedQuery.setParameter("selectedItem", itemName);
		List<ListItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		}
	public ListItem
	searchForItemById(int idToEdit) {
	EntityManager em = emfactory.createEntityManager();
	em.getTransaction().begin();
	ListItem found = em.find(ListItem.class, idToEdit);
	em.close();
	return found;
	}
	public void updateItem(ListItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		


	}
	public void cleanUp(){
		emfactory.close();
		}
	
}

