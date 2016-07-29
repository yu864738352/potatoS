package share.top.com.phone.beans;

/**
 * Created by ZHOU on 2016/3/9.
 */
public class Persion {
    private String name;
    private String number;
    public Persion(String name, String number) {
        this.name = name;
        this.number = number;
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
