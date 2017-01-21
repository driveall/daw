package db;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ItemsList implements Serializable{
    private List<Integer> itemsIDsList = new LinkedList<>();

    public ItemsList() {
    }

    public List<Integer> getItemsIDsList() {
        return itemsIDsList;
    }

    public void setItemsIDsList(List<Integer> itemsIDsList) {
        this.itemsIDsList = itemsIDsList;
    }
    
}
