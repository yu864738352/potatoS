package share.top.com.phone.beans;

/**
 * Created by ZHOU on 2016/3/10.
 */
public class StepsBean {

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public StepsBean(String img, String step) {

        this.img = img;
        this.step = step;
    }

    private String img;
    private String step;
}
