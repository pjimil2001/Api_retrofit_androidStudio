package com.example.apidemo.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apidemo.R;
import com.example.apidemo.utility.GlideImageLoader;
import com.example.apidemo.utility.Utility;

public class MainActivity2 extends AppCompatActivity
{
    ImageView img;
    TextView tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1=(TextView)findViewById(R.id.id);
        img=(ImageView)findViewById(R.id.img);
        tv2=(TextView)findViewById(R.id.first);
        tv3=(TextView)findViewById(R.id.last);
        tv4=(TextView)findViewById(R.id.mail);



        tv1.setText(getIntent().getStringExtra("id"));

        img.setImageResource(getIntent().getIntExtra("img",0));

         new GlideImageLoader(null, null).load(null, Utility.getGlideRequestOptions());

        tv2.setText(getIntent().getStringExtra("first"));
        tv3.setText(getIntent().getStringExtra("last"));
        tv4.setText(getIntent().getStringExtra("mail"));
    }



}

// extends AppCompatActivity {
//
//
//
//    APIInterface apiinterface;
//
//    RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//
//
//        recyclerView=findViewById(R.id.recyclerView);
//
//
//
//
//        apiinterface= retrofitinstance.getRetrofit().create(APIInterface.class);
//        apiinterface.getpost().enqueue(new Callback<List<mo>>() {
//            @Override
//            public void onResponse(Call<List<mo>> call, Response<List<mo>> response) {
//                if (response.body().size() > 0 ) {
//
//                    List<mo>postlist =response.body();
//                    readapter adapter=new readapter(MainActivity2.this, postlist);
//                    LinearLayoutManager linearLayoutManager= new LinearLayoutManager(MainActivity2.this);
//                    recyclerView.setLayoutManager(linearLayoutManager);
//                    recyclerView.setAdapter(adapter);
//
//
//                } else{
//                    Toast.makeText(MainActivity2.this, "empty", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<mo>> call, Throwable t) {
//                Toast.makeText(MainActivity2.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//
//
//
//
//
//
//    }
//}