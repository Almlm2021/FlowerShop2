package Repository;

import MiraMaro.com.Entities.Customer;
import MiraMaro.com.Entities.Product;
import MiraMaro.com.configurations.DBConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductRepository {
    private Session session;
    private static ProductRepository instance;

    private ProductRepository() {
        this.session = DBConfiguration.session;
    }

    public static ProductRepository getInstance()
    {
        if(instance==null)
        {
            instance=new ProductRepository();
        }
        return instance;
    }

    public void save(Product product) {
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();

    }


    public Product findById(int id) {
        return session.get(Product.class, id);
    }


    public List<Product> findAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        cq.from(Product.class);
        Query<Product> query = session.createQuery(cq);
        return query.getResultList();
    }


    public void update(Product product) {
        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
    }


    public void delete(Product product) {
        Transaction transaction = session.beginTransaction();
        session.delete(product);
        transaction.commit();
    }



}
