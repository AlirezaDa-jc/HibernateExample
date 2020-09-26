package ir.maktab.repository;

import ir.maktab.entities.Admin;
import ir.maktab.services.AdminService;

import javax.persistence.*;


public class AdminRepository {
    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public AdminRepository() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }
    
    public boolean adminLogin(String userName, String password) {
        em.getTransaction().begin();
        TypedQuery<Admin> query = em.createQuery(
                "SELECT a FROM Admin  a where a.name=:name and a.password =:password",
                Admin.class);
        query.setParameter("name",userName);
        query.setParameter("password",password);
        if(query.getResultList().size() > 0) {
            Admin a= query.getSingleResult();
            AdminService.setAdmin(a);
            System.out.println("Welcome BaCk!"+a.getName() +" ^_^ ");
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }

}
