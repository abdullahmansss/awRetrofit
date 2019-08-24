package softagi.androidworkshop2.Models;

import android.content.ClipData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root
{
    @SerializedName("items")
    private List<Items> items = null;

    public List<Items> getItems()
    {
        return items;
    }

    public void setItems(List<Items> items)
    {
        this.items = items;
    }
}
