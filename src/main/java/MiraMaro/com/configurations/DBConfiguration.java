package MiraMaro.com.configurations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConfiguration {
   public static Configuration configuration = new Configuration().configure();
   public static SessionFactory sessionFactory = configuration.buildSessionFactory();
   public static Session session = sessionFactory.openSession();
}
