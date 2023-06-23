package Repository;

import MiraMaro.com.Entities.Admin;
import MiraMaro.com.configurations.DBConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdminRepository {
    private Session session;
    private static AdminRepository instance;

    private AdminRepository() {
        this.session = DBConfiguration.session;
    }
    public static AdminRepository getInstance(){
        if(instance==null){
            instance=new AdminRepository();
        }
        return instance;
    }


    public void save(Admin admin) {
        Transaction transaction = session.beginTransaction();
        session.save(admin);
        transaction.commit();
    }


    public Admin findById(int id) {
        return session.get(Admin.class, id);
    }

    public List<Admin> findAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
        cq.from(Admin.class);
        Query<Admin> query = session.createQuery(cq);
        return query.getResultList();
    }

    public void update(Admin admin) {
        Transaction transaction = session.beginTransaction();
        session.update(admin);
        transaction.commit();
    }

    public void delete(Admin admin) {
        Transaction transaction = session.beginTransaction();
        session.delete(admin);
        transaction.commit();
    }
    public Admin findByEmail(String email) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Admin> cq = cb.createQuery(Admin.class);
        Root<Admin> root = cq.from(Admin.class);
        cq.select(root).where(cb.equal(root.get("email"), email));
        Query<Admin> query = session.createQuery(cq);
        return query.uniqueResult();
    }
}
