package ir.maktab.repository;

import ir.maktab.entities.Article;
import ir.maktab.entities.User;
import ir.maktab.services.UserService;

import javax.persistence.*;
import java.util.Set;

public class ArticleRepository {
    private static EntityManager em = null;
    private EntityManagerFactory emf = null;

    public ArticleRepository() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    public void add(Article article) {
        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();
    }

    public void displayAUserArticle() {
        em.getTransaction().begin();
        User user = UserService.getUser();
        TypedQuery<Article> query = em.createQuery(
                "SELECT a FROM Article a where a.user=:user",
                Article.class);
        query.setParameter("user", user);
        for (int i = 0; i < query.getResultList().size(); i++) {
            Article a = query.getResultList().get(i);
            System.out.println("ID: " + a.getId() + "\nTitle: " + a.getTitle() + "\nBrief: " + a.getBrief()
                    + "\nContent: " + a.getContent() + "\nCreate Date: " + a.getCreateDate() + "\nAuthor: " +
                    a.getUser().getName() + "\nIs Published: " + a.isPublished() + "\nCategory: "
                    + a.getCategory().getTitle());
        }
        em.getTransaction().commit();
    }

    public void update(Article article) {

        em.getTransaction().begin();
        em.persist(article);
        em.getTransaction().commit();

    }

    public Article getArticle(int id) {
        em.getTransaction().begin();
        TypedQuery<Article> query = em.createQuery(
                "SELECT a FROM Article a where a.id=:id",
                Article.class);
        query.setParameter("id", id);
        em.getTransaction().commit();
        return query.getSingleResult();
    }

    public int deleteArticleUser(int id, User user) {
        Set<Article> articleSet = user.getArticles();
        for (Article article : articleSet) {
            if (article.getId() == id) {
                return delete(id);
            }
        }
        System.out.println("Invalid ID");
        return 0;
    }

    public int delete(int id) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Article a where a.id =:id");
        query.setParameter("id", id);
        int executeUpdates = query.executeUpdate();
        em.getTransaction().commit();
        return executeUpdates;
    }

    public void displayAll() {
        em.getTransaction().begin();
        TypedQuery<Article> query = em.createQuery(
                "SELECT a FROM Article a ",
                Article.class);
        for (int i = 0; i < query.getResultList().size(); i++) {
            Article a = query.getResultList().get(i);
            System.out.println("ID: " + a.getId() + "\nTitle: " + a.getTitle() + "\nBrief: " + a.getBrief());
        }
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }
}
