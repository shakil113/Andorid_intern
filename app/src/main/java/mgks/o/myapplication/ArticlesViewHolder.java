package mgks.o.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticlesViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView id , user,name, who;


    public  ArticlesViewHolder(View itemView){

        super(itemView);
        image = itemView.findViewById(R.id.image);
        id = itemView.findViewById(R.id.id);
        user =  itemView.findViewById(R.id.user);
        name = itemView.findViewById(R.id.name);
        who = itemView.findViewById(R.id.who);



    }
}