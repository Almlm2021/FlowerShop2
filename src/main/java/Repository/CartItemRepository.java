package Repository;

import MiraMaro.com.Entities.CartItem;
import MiraMaro.com.Entities.Product;
import MiraMaro.com.configurations.DBConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CartItemRepository {
    private Session session;

    public CartItemRepository() {
        this.session = DBConfiguration.session;
    }


    public void save(CartItem cartItem) {
        Transaction transaction = session.beginTransaction();
        session.save(cartItem);
        transaction.commit();

    }


    public CartItem findById(int id) {
        return session.get(CartItem.class, id);
    }


    public List<CartItem> findAll() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CartItem> cq = cb.createQuery(CartItem.class);
        cq.from(CartItem.class);
        Query<CartItem> query = session.createQuery(cq);
        return query.getResultList();
    }


    public void update(CartItem cartItem) {
        Transaction transaction = session.beginTransaction();
        session.update(cartItem);
        transaction.commit();
    }


    public void delete(CartItem cartItem) {
        Transaction transaction = session.beginTransaction();
        session.delete(cartItem);
        transaction.commit();
    }
}
