package com.example.tareaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tareaapi.interfaces.JournalAPI;
import com.example.tareaapi.models.Journal;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText txtJournalID;
    TextView txtListado;
   // Button btRetrofit;
  //  String URL="https://revistas.uteq.edu.ec/ws//issues.php";
    String color="#52734d";
    private RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtJournalID = findViewById(R.id.txtJournalID);
       // btRetrofit = findViewById(R.id.btRetrofit);
        txtListado = findViewById(R.id.txtListado);
        rq= Volley.newRequestQueue(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));

    }
    public void btRetrofit(View view){
        find(txtJournalID.getText().toString());
    }

    private void find(String j_id){
            Retrofit retrofit=new Retrofit.Builder().baseUrl("https://revistas.uteq.edu.ec/ws/")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            JournalAPI journalAPI=retrofit.create(JournalAPI.class);
            Call<List<Journal>>  call=journalAPI.find(j_id);
            call.enqueue(new Callback<List<Journal>>() {
                @Override
                public void onResponse(Call<List<Journal>>  call, Response<List<Journal>> response) {
                    try {
                        if(response.isSuccessful()){
                            List<Journal> r= response.body();
                            String c="";
                            for (int i=0;i<r.size();i++){
                                Journal p=r.get(i);
                                c+= p.toString();
                            }
                            txtListado.setText(c);
                        }
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Journal>>  call, Throwable t) {

                }
            });

        }

    public void recuperar(View v){
        txtListado.setText("");
        String dir="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+txtJournalID.getText().toString();

        JsonArrayRequest requerimiento=new JsonArrayRequest
                (Request.Method.GET,dir,null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Journal journal= new Journal();
                        List<String> journalList =new ArrayList<String>();
                        for(int f=0;f<response.length();f++)
                        {

                            try {
                                JSONObject objeto=new JSONObject(response.get(f).toString());
                                journal.setIssue_id("issue_id: "+objeto.get("issue_id").toString());
                                journal.setVolume("volume: "+objeto.get("volume").toString());
                                journal.setNumber("number: "+objeto.get("number").toString());
                                journal.setYear("year: "+objeto.get("year").toString());
                                journal.setDate_published("date_published: "+objeto.get("date_published").toString());
                                journal.setTitle("title: " +objeto.get("title").toString());
                                journal.setDoi("doi: "+objeto.get("doi").toString());
                                journal.setCover("cover: "+objeto.get("cover").toString()+"\n");

                                journalList.add(journal.getIssue_id());
                                journalList.add(journal.getVolume());
                                journalList.add(journal.getNumber());
                                journalList.add(journal.getYear());
                                journalList.add(journal.getDate_published());
                                journalList.add(journal.getTitle());
                                journalList.add(journal.getDoi());
                                journalList.add(journal.getCover());
                                journalList.add("_______________________________________\n");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        String c="";
                        for (int i=0;i<journalList.size();i++){
                            c+= journalList.get(i).toString()+"\n";

                        }
                        txtListado.setText(c);

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        rq.add(requerimiento);
    }

}
