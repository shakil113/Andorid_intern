package mgks.o.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.myjson.com/bins/15baeq";
    RecyclerView recyclerView;
    ArticlesAdaptor adaptor;
    ArrayList<Article> articles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptor = new ArticlesAdaptor();
        recyclerView.setAdapter(adaptor);
        articles = new ArrayList<>();
        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    try {

                        JSONArray jsonArray = response.getJSONArray("search_result");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);

                            Article article = new Article();
                            article.setImage(jsonObject.getString("image"));
                            article.setId(jsonObject.getString("id"));
                            article.setUser(jsonObject.getString("user"));
                            article.setName(jsonObject.getString("name"));
                            article.setWho(jsonObject.getString("who"));

                            articles.add(article);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                adaptor.setData(articles);
                adaptor.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}