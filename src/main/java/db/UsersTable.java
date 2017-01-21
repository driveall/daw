package db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "daw_users")
public class UsersTable implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Column(name = "lvl")
    private Integer lvl;
    @Column(name = "points")
    private Integer points;
    @Size(max = 255)
    @Column(name = "items")
    private String items;
    @Size(max = 255)
    @Column(name = "items_all")
    private String itemsAll;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 255)
    @Column(name = "pass")
    private String pass;
    @Size(max = 6)
    @Column(name = "sex")
    private String sex;
    @Column(name = "money")
    private Integer money;
    @Column(name = "email")
    private String email;

    public UsersTable() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public void setItemsAll(String itemsAll) {
        this.itemsAll = itemsAll;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public UsersTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public UsersTable withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UsersTable withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getLvl() {
        return lvl;
    }

    public UsersTable withLvl(Integer lvl) {
        this.lvl = lvl;
        return this;
    }

    public Integer getPoints() {
        return points;
    }

    public UsersTable withPoints(Integer points) {
        this.points = points;
        return this;
    }

    public String getItems() {
        return items;
    }

    public UsersTable withItems(String items) {
        this.items = items;
        return this;
    }

    public String getItemsAll() {
        return itemsAll;
    }

    public UsersTable withItemsAll(String itemsAll) {
        this.itemsAll = itemsAll;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public UsersTable withDate(Date date) {
        this.date = date;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public UsersTable withPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public UsersTable withSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Integer getMoney() {
        return money;
    }

    public UsersTable withMoney(Integer money) {
        this.money = money;
        return this;
    }
    public String getEmail() {
        return email;
    }

    public UsersTable withEmail(String email) {
        this.email = email;
        return this; 
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersTable)) {
            return false;
        }
        UsersTable other = (UsersTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "driveall.daw.UsersTable[ id=" + id + " ]";
    }
    
}
