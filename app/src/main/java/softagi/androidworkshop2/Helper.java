package softagi.androidworkshop2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import softagi.androidworkshop2.Models.Items;

public interface Helper
{
    @GET("books/v1/volumes")
    Call<List<Items>> getBooks(@Query("q") String q);
}
