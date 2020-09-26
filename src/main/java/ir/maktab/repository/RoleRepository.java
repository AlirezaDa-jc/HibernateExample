package ir.maktab.repository;

import ir.maktab.entities.Role;
import ir.maktab.services.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class RoleRepository {
    private static EntityManager em = null;
    private EntityManagerFactory emf = null;

    public RoleRepository() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        em = emf.createEntityManager();
    }

    public static void close(){
        em.close();
    }

    public boolean check(Role role) {
        em.getTransaction().begin();
        TypedQuery<Role> query = em.createQuery(
                "SELECT u FROM Role u where u.roleTitle=:title",
                Role.class);

        query.setParameter("title", role.getRoleTitle());
        return query.getResultList().size() > 0;
    }

    public void update(Role role) {
        em.getTransaction().begin();
        em.persist(role);
        RoleService.setRole(role);
        em.getTransaction().commit();
    }
}
