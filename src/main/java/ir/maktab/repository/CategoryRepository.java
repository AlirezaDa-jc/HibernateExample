package ir.maktab.repository;

import ir.maktab.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CategoryRepository {
    private static EntityManager em = null;
    private EntityManagerFactory emf = null;
    public CategoryRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    public static void add(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    public void display() {
        em.getTransaction().begin();
        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c ",
                Category.class);
        for (int i = 0; i < query.getResultList().size(); i++) {
            Category c = query.getResultList().get(i);
            System.out.println("ID: " + c.getId() + "\nTitle: " + c.getTitle() + "\nDescription: " + c.getDescription());
        }
        em.getTransaction().commit();
    }


    public static boolean checkDuplicateTitle(String title) {
        em.getTransaction().begin();
        TypedQuery<Category> query = em.createQuery(
                "SELECT u FROM Category u where u.title=:title",
                Category.class);

        query.setParameter("title", title);
        if (query.getResultList().size() > 0) {
            em.getTransaction().rollback();
            return false;
        }
        em.getTransaction().commit();
        return true;
    }

    public static Category use(String title) {
        em.getTransaction().begin();
        TypedQuery<Category> query = em.createQuery(
                "SELECT u FROM Category u where u.title=:title",
                Category.class);

        query.setParameter("title", title);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        }
        return null;
    }

    public void close() {
        emf.close();
    }
}
