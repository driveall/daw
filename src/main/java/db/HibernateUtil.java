package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sf = null;    
    static {
        sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();        
    }    
    public static SessionFactory getSessionFactory() {
        return sf;
    }
}
