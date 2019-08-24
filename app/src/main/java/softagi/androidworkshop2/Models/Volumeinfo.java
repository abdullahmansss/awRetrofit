package softagi.androidworkshop2.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Volumeinfo
{
    @SerializedName("title")
    private String title;
    @SerializedName("authors")
    private List<String> authors = null;
    @SerializedName("imageLinks")
    private ImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}
