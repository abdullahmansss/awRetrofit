package softagi.androidworkshop2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import softagi.androidworkshop2.Models.Items;
import softagi.androidworkshop2.Models.Root;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    ProgressBar progressBar;
    RecyclerView.LayoutManager layoutManager;

    bookAdapter adapter;
    Retrofit retrofit;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressbar);

        progressBar.setVisibility(View.INVISIBLE);

        layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        helper = retrofit.create(Helper.class);

        getBooks();
    }

    private void getBooks()
    {
        Call<List<Items>> call = helper.getBooks("cars");

        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response)
            {
                if (response.isSuccessful())
                {
                    List<Items> i = response.body();

                    adapter = new bookAdapter(i);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t)
            {

            }
        });
    }

    class bookAdapter extends RecyclerView.Adapter<bookAdapter.bookVH>
    {
        List<Items> items;

        bookAdapter(List<Items> bookModels)
        {
            this.items = bookModels;
        }

        @NonNull
        @Override
        public bookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
            return new bookVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull bookVH holder, int position)
        {
            Root root = new Root();
            items = root.getItems();

            Items items1 = items.get(position);

            String t = items1.getVolumeInfo().getTitle();
            String a = items1.getVolumeInfo().getAuthors().get(0);
            String i = items1.getVolumeInfo().getImageLinks().getThumbnail();

            holder.bookTitle.setText(t);
            holder.bookAuthor.setText(a);

            if (i.isEmpty())
            {
                holder.bookImage.setImageResource(R.drawable.ic_launcher_background);
            } else
            {
                Picasso.get()
                        .load(i)
                        .into(holder.bookImage);
            }
        }

        @Override
        public int getItemCount()
        {
            return items.size();
        }

        class bookVH extends RecyclerView.ViewHolder
        {
            ImageView bookImage;
            TextView bookTitle,bookAuthor;

            bookVH(@NonNull View itemView)
            {
                super(itemView);

                bookImage = itemView.findViewById(R.id.book_img);
                bookTitle = itemView.findViewById(R.id.book_title);
                bookAuthor = itemView.findViewById(R.id.book_author);
            }
        }
    }
}