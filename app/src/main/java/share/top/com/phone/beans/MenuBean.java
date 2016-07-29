package share.top.com.phone.beans;

import android.view.Menu;

/**
 * Created by ZHOU on 2016/3/10.
 */
public class MenuBean {
    public MenuBean() {
    }

    public MenuBean(String id, String tags, String imtro, String ingredients, String burden, String albums) {
        this.id = id;
        this.tags = tags;
        this.imtro = imtro;
        this.ingredients = ingredients;
        this.burden = burden;
        this.albums = albums;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }

    private String id;
    private String tags;
    private String imtro;
    private String ingredients;
    private String burden;
    private String albums;
}
