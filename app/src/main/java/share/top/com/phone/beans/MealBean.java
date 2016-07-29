package share.top.com.phone.beans;

/**
 * Created by ZHOU on 2016/3/8.
 */
public class MealBean {

    private int id;
    private String number;
    private String name;

    public MealBean(int id, String number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
