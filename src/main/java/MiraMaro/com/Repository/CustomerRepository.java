package MiraMaro.com.Repository;

import MiraMaro.com.Entities.Customer;
import MiraMaro.com.configurations.DBConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerRepository {
    private Session session;
    private static CustomerRepository instance;

    private CustomerRepository() {
        this.session = DBConfiguration.session;
    }
    public static CustomerRepository getInstance(){
        if(instance==null){
            instance=new CustomerRepository();
        }
        return instance;
    }


    public void save(Customer customer) {
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
    }

    public Customer findById(int id) {
        return session.get(Customer.class, id);
    }

    public List<Customer> findAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        cq.from(Customer.class);
        Query<Customer> query = session.createQuery(cq);
        return query.getResultList();
    }

    public void update(Customer customer) {
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
    }

    public void delete(Customer customer) {
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
    }
    public Customer findByEmail(String email) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        cq.select(root).where(cb.equal(root.get("email"), email));
        Query<Customer> query = session.createQuery(cq);
        return query.uniqueResult();
    }
}
