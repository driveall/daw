package daw;

import com.google.gson.Gson;
import db.UsersTable;
import dao.Data;
import db.ItemsList;
import db.ItemsTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class UserManager {

    public boolean regNewUser(String name, String pass, String sex) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        if (d.getUserByName(name) != null) {
            return false;
        } else {
            Gson gson = new Gson();
            UsersTable u = new UsersTable(new Random().nextInt());
            u.withName(name).withPass(pass).withSex(sex).withLvl(0).withMoney(100).withPoints(0).withDate(new Date()).withItemsAll(gson.toJson(new ItemsList())).withItems(gson.toJson(new ItemsList()));
            d.addNewUser(u);
            return true;
        }
    }

    public boolean logUser(String name, String pass) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        UsersTable user = d.getUserByName(name);
        return user != null && user.getPass().equals(pass);
    }

    public UsersTable getUserByName(String name) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        return d.getUserByName(name);
    }

    public void updateUser(UsersTable u) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        d.updateUser(u);
    }

    public UsersTable updateLevel(UsersTable u) {
        Integer points = u.getPoints();
        Integer level = u.getLvl();
        if (level == 0) {
            if (points > 9) {
                level = 1;
            }
        } else {
            if (points >= (20 * level) + (5 * level * level * level)) {
                level++;
            }
        }
        u.setLvl(level);
        return u;
    }

    private void addNewItem(Integer itemID, UsersTable user) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        Gson gson = new Gson();
        String jsonItems = user.getItemsAll();
        List<Integer> itemsIDs = gson.fromJson(jsonItems, ItemsList.class).getItemsIDsList();
        itemsIDs.add(itemID);
        ItemsList ite = new ItemsList();
        ite.setItemsIDsList(itemsIDs);
        jsonItems = gson.toJson(ite);
        user.setItemsAll(jsonItems);
        d.updateUser(user);
    }

    public void sellItem(HttpServletRequest request, HttpServletResponse response, UsersTable user) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        Gson gson = new Gson();
        Integer itemForSell = Integer.parseInt(request.getParameter("sell"));
        ItemsTable item = d.getItemByID(itemForSell);
        Integer sellingCost = item.getPrice() / 2;
        user.setMoney(user.getMoney() + sellingCost);
        ItemsList itemsAll = gson.fromJson(user.getItemsAll(), ItemsList.class);
        List<Integer> itemsIDs = itemsAll.getItemsIDsList();
        itemsIDs.remove(itemForSell);
        itemsAll.setItemsIDsList(itemsIDs);
        user.setItemsAll(gson.toJson(itemsAll));
        d.updateUser(user);
        try {
            response.sendRedirect("/daw/user");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addItemToHands(HttpServletRequest request, UsersTable user) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        Gson gson = new Gson();
        Integer itemID = Integer.parseInt(request.getParameter("add"));
        ItemsTable itemToAdd = d.getItemByID(itemID);
        Integer itemLevel = itemToAdd.getLvl();
        if(itemLevel <= user.getLvl()){
            List<Integer> itemsAll = gson.fromJson(user.getItemsAll(), ItemsList.class).getItemsIDsList();
            List<Integer> itemsOn = gson.fromJson(user.getItems(), ItemsList.class).getItemsIDsList();
            boolean possible = true;
            if (itemsAll.contains(itemID)) {
                for (Integer i : itemsOn) {
                    ItemsTable item = d.getItemByID(i);
                    if (item.getCat().equals(itemToAdd.getCat())) {
                        possible = false;
                    }
                }
                if (possible) {
                    itemsAll.remove(itemID);
                    itemsOn.add(itemID);
                    ItemsList l = (ItemsList)SpringSingle.getContext().getBean("iList");
                    l.setItemsIDsList(itemsAll);
                    user.setItemsAll(gson.toJson(l));
                    l.setItemsIDsList(itemsOn);
                    user.setItems(gson.toJson(l));
                    d.updateUser(user);
                }
            }
        }
    }

    public void remItemFromHands(HttpServletRequest request, UsersTable user) {
        Data d = (Data)SpringSingle.getContext().getBean("data");
        Integer itemID = Integer.parseInt(request.getParameter("rem"));
        Gson gson = new Gson();
        List<Integer> itemsAll = gson.fromJson(user.getItemsAll(), ItemsList.class).getItemsIDsList();
        List<Integer> itemsOn = gson.fromJson(user.getItems(), ItemsList.class).getItemsIDsList();
        if (itemsOn.contains(itemID)) {
            itemsOn.remove(itemID);
            itemsAll.add(itemID);
            ItemsList l = (ItemsList)SpringSingle.getContext().getBean("iList");
            l.setItemsIDsList(itemsAll);
            user.setItemsAll(gson.toJson(l));
            l.setItemsIDsList(itemsOn);
            user.setItems(gson.toJson(l));
            d.updateUser(user);
        }
    }

    
    public void clearAllAttributes(HttpSession s) {
        Enumeration<String> attrs = s.getAttributeNames();
        while (attrs.hasMoreElements()) {
            s.removeAttribute(attrs.nextElement());
        }
    }

    public String buyItem(String userName, ItemsTable item) {
        UserManager um = (UserManager)SpringSingle.getContext().getBean("user");
        UsersTable user = um.getUserByName(userName);
        Integer money = user.getMoney();
        String s = "";
        if (item.getPrice() > money) {
            s += "Not_enough_money_for_buying_" + item.getName() + "<hr>";
        } else {
            s += item.getName() + "_buyed_for_" + item.getPrice() + "_money<hr>";
            money = money - item.getPrice();
            user.setMoney(money);
            um.addNewItem(item.getId(), user);
            um.updateUser(user);
        }
        return s;
    }
    public List<ItemsTable> getUserItemsList(UsersTable user){
        Data d = (Data)SpringSingle.getContext().getBean("data");
        Gson gson = new Gson();
        List<Integer> items = gson.fromJson(user.getItems(), ItemsList.class).getItemsIDsList();
        List<ItemsTable> ret = new LinkedList<ItemsTable>();
        for(Integer i:items){
            ret.add(d.getItemByID(i));
        }
        return ret;
    }
    
}
