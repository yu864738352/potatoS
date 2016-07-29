package share.top.com.phone.beans;

import java.io.Serializable;

/**
 * Created by ZHOU on 2016/3/8.
 */
public class OperatorBean implements Serializable {
    private int id;
    private String name;
    private String number;

    public OperatorBean(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
