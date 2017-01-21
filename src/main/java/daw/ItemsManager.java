package daw;

import dao.Data;
import db.ItemsTable;
import java.util.LinkedList;
import java.util.List;

public class ItemsManager {
    public List<ItemsTable> getItemsList(){
        Data d = (Data)SpringSingle.getContext().getBean("data");
        return d.getItems();
    }
    private List<ItemsTable> getCategoryItemsList(String cat){
        Data d = (Data)SpringSingle.getContext().getBean("data");
        List<ItemsTable> items = d.getItems();
        List<ItemsTable> catItems = new LinkedList<ItemsTable>();
        for(ItemsTable i:items){
            if(i.getCat().equals(cat)){
                catItems.add(i);
            }
        }
        return catItems;
    }
    public List<List<ItemsTable>> getListCatLists(){
        List<ItemsTable> helmets = getCategoryItemsList("helmet");
        List<ItemsTable> armors = getCategoryItemsList("armor");
        List<ItemsTable> bootses = getCategoryItemsList("boots");
        List<ItemsTable> phones = getCategoryItemsList("phone");
        List<ItemsTable> weapons = getCategoryItemsList("hands");
        List<List<ItemsTable>> ret = new LinkedList<List<ItemsTable>>();
        ret.add(helmets);
        ret.add(armors);
        ret.add(bootses);
        ret.add(phones);
        ret.add(weapons);
        return ret;
    }
    public ItemsTable getItemByID(int id){
        Data d = (Data)SpringSingle.getContext().getBean("data");
        return d.getItemByID(id);
    }
    
}
