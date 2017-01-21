package dao;

import db.HibernateUtil;
import db.ItemsTable;
import db.UsersTable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

public class Data {

    public void addNewUser(UsersTable u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();
    }

    public List<UsersTable> getAllUsers() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(UsersTable.class);
        List<UsersTable> l = c.list();
        s.close();
        return l;
    }

    public UsersTable getUserByName(String name) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(UsersTable.class);
        List<UsersTable> l = c.list();
        s.close();
        for(UsersTable u:l){
            if(u.getName().equalsIgnoreCase(name)){
                return u;
            }
        }
        return null;
    }

    public void updateUser(UsersTable u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(u);
        s.getTransaction().commit();
        s.close();
    }

    public void deleteUser(UsersTable u) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(u);
        s.getTransaction().commit();
        s.close();
    }

    public List<ItemsTable> getItems() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Criteria c = s.createCriteria(ItemsTable.class);
        List<ItemsTable> items = c.list();
        s.close();
        return items;
    }

    public ItemsTable getItemByID(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from ItemsTable where id = '"+id+"' ");
        List<ItemsTable> l = q.list();
        s.getTransaction().commit();
        s.close();        
        return l.get(0);
    }
    public List<ItemsTable> sortItems(List<ItemsTable> items){
        for(int i = 0; i < items.size(); i++){
            for(int j = 0; j < items.size()-1-i; j++){
                if(items.get(j).getId()>items.get(j+1).getId()){
                    ItemsTable iThis = items.get(j);
                    items.set(j, items.get(j+1));
                    items.set(j+1, iThis);
                }
            }
        }
        return items;
    }
    public void updateItemsTable(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<ItemsTable> items = getItems();
        items = sortItems(items);
        s.beginTransaction();
        for(int i = 0; i < items.size(); i++){
            s.delete(items.get(i));
        }
        s.getTransaction().commit();        
        for(int i = 0; i < items.size(); i++){
            uITHelper(items.get(i));
        }
        s.close();      
    }
    public void uITHelper(ItemsTable item){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(item);
        s.getTransaction().commit();
        s.close();
    }
}
