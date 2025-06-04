package lab.data;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import lab.entities.Complaint;

import java.util.List;

@ApplicationScoped
public class ComplaintRepository {

    @Inject
    private EntityManager em;

    public void create(Complaint entity) {
        em.persist(entity);
    }

    public void edit(Complaint entity) {
        em.merge(entity);
    }

    public void remove(Complaint entity) {
        em.remove(em.merge(entity));
    }

    public Complaint find(Object id) {
        return em.find(Complaint.class, id);
    }

    public List<Complaint> findAll(String status) {
        if (status != null && !"".equals(status)) {
            return em.createNamedQuery("Complaint.findByStatus", Complaint.class)
                    .setParameter("status", status)
                    .getResultList();
        } else {
            return em.createQuery("SELECT c FROM Complaint c", Complaint.class)
                    .getResultList();
        }
    }

}
