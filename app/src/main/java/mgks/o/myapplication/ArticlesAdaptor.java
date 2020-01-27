package mgks.o.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticlesAdaptor extends RecyclerView.Adapter<ArticlesViewHolder> {

    ArrayList<Article> articles;

    public ArticlesAdaptor() {
        articles = new ArrayList<>();
    }

    public void setData(ArrayList<Article> articles){
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View articleView = layoutInflater.inflate(R.layout.recycler_row,parent,false);


        return new ArticlesViewHolder(articleView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        Article article = articles.get(position);
        Picasso.get()
                .load(article.image)
                .into(holder.image);
        holder.id.setText(article.id);
        holder.user.setText(article.user);
        holder.name.setText(article.name);
        holder.who.setText(article.who);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
