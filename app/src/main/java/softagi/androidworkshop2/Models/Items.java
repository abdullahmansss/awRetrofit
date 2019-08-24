package softagi.androidworkshop2.Models;

import com.google.gson.annotations.SerializedName;

public class Items
{
    @SerializedName("volumeInfo")
    private Volumeinfo volumeInfo;

    public Volumeinfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(Volumeinfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
