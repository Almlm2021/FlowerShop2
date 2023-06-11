package Repository;


import MiraMaro.com.Entities.Cart;
import MiraMaro.com.configurations.DBConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CartRepository {
    private Session session;
    private static CartRepository instance;

    private CartRepository() {
        this.session = DBConfiguration.session;
    }

    public static CartRepository getInstance(){
        if(instance==null){
            instance=new CartRepository();
        }
        return instance;
    }

    public void save(Cart cart) {
        Transaction transaction = session.beginTransaction();
        session.save(cart);
        transaction.commit();

    }


    public Cart findById(int id) {
        return session.get(Cart.class, id);
    }


    public List<Cart> findAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cart> cq = cb.createQuery(Cart.class);
        cq.from(Cart.class);
        Query<Cart> query = session.createQuery(cq);
        return query.getResultList();
    }


    public void update(Cart cart) {
        Transaction transaction = session.beginTransaction();
        session.update(cart);
        transaction.commit();
    }


    public void delete(Cart cart) {
        Transaction transaction = session.beginTransaction();
        session.delete(cart);
        transaction.commit();
    }
}

