package share.top.com.phone.beans;

/**
 * Created by ZHOU on 2016/2/25.
 */
public class ContentModel {

    private int Image;
    private String text;

    public ContentModel(int image, String text) {
        Image = image;
        this.text = text;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
