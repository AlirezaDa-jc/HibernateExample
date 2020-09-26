package ir.maktab.repository;

import ir.maktab.entities.User;
import ir.maktab.services.UserService;

import javax.persistence.*;

public class UserRepository {

    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public UserRepository() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }


    public void userSignIn(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public boolean userLogin(String userName, String password) {
        em.getTransaction().begin();
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u where u.name=:name and u.password =:password",
                User.class);

        query.setParameter("name",userName);
        query.setParameter("password",password);
        if(query.getResultList().size() > 0) {
            User u= query.getSingleResult();
            UserService.setUser(u);
            System.out.println("Welcome BaCk!"+u.getName() +" ^_^ ");
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }

    public void update(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }
}
