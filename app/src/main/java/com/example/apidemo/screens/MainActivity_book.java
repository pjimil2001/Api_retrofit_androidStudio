package com.example.apidemo.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apidemo.R;
import com.example.apidemo.api.APIClient;
import com.example.apidemo.api.APIInterface;
import com.example.apidemo.api.NoConnectivityException;
import com.example.apidemo.models.bookDetails;
import com.example.apidemo.models.bookResponse;
import com.example.apidemo.models.cdDetails;
import com.example.apidemo.models.cdResponse;
import com.example.apidemo.utility.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_book extends AppCompatActivity {

    RecyclerView recyclerView;
    MainActivity_book.UserAdapter adapter;
    LinearLayout llProgressBar, llEmptyView;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        recyclerView = findViewById(R.id.recyclerView);
        llProgressBar = findViewById(R.id.llProgressBar);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        llEmptyView = findViewById(R.id.llEmptyView);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity_book.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUs();
            }
        });

        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
                getUs();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getUs();
    }

    public void getUs() {
        llProgressBar.setVisibility(View.VISIBLE);
        APIClient
                .getClient(MainActivity_book.this)
                .create(APIInterface.class)
                .getUs()
                .enqueue(new Callback<bookResponse>() {
                    @Override
                    public void onResponse(Call<bookResponse> call, Response<bookResponse> response) {
                        llProgressBar.setVisibility(View.GONE);
                        if (swipeRefresh.isRefreshing())
                            swipeRefresh.setRefreshing(false);
                        if (response.code() == 200) {
                            if (response.body() != null) {
                                bookResponse apiResponse = response.body();

                                if (apiResponse.getResult_all_books().size() > 0) {
                                    adapter = new MainActivity_book.UserAdapter(MainActivity_book.this, apiResponse.getResult_all_books());
                                    recyclerView.setAdapter(adapter);
                                    if (apiResponse.getResult_all_books().size() > 0) {
                                        llEmptyView.setVisibility(View.GONE);
                                    } else {
                                        llEmptyView.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    llEmptyView.setVisibility(View.VISIBLE);
                                }
                            } else {
                                llEmptyView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Utility.showError(MainActivity_book.this, response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<bookResponse> call, Throwable t) {
                        llProgressBar.setVisibility(View.GONE);
                        if (swipeRefresh.isRefreshing())
                            swipeRefresh.setRefreshing(false);
                        Log.e("onFailure", t.toString() + "");
                        if (t instanceof NoConnectivityException) {
                            Toast.makeText(MainActivity_book.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        call.cancel();
                    }
                });
    }

    class UserAdapter extends RecyclerView.Adapter<MainActivity_book.UserAdapter.MyViewHolder> {

        private List<bookDetails> listData;
        Activity context;

        public UserAdapter(Activity context, List<bookDetails> listData) {
            this.context = context;
            this.listData = listData;
        }

        @Override
        public MainActivity_book.UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view, parent, false);

            return new MainActivity_book.UserAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MainActivity_book.UserAdapter.MyViewHolder holder, int position) {

            bookDetails data = listData.get(position);

//            new GlideImageLoader(holder.imgUser, holder.progress).load(data.getAvatar(), Utility.getGlideRequestOptions());


//            holder.tvName.setText(data.getFirst_name() + "    " + data.getLast_name());
            holder.tvEmail.setText(data.getDetails());
//            holder.id.setText(data.getId());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent i =new Intent(MainActivity.this,MainActivity2.class);
//                    startActivity(i);


//                    if  (holder.id.getId()==0){
//                        Intent i = new Intent(MainActivity.this,MainActivity2.class);
//                        startActivity(i);
//
//                    }else {
//                        Toast.makeText(MainActivity.this, "empty", Toast.LENGTH_SHORT).show();
//                    }


                    Intent intent=new Intent(context,MainActivity2.class);

//                    intent.putExtra("id",data.getId());
//                    intent.putExtra("img",data.getAvatar());
//                    intent.putExtra("first",data.getFirst_name());
//                    intent.putExtra("last",data.getLast_name());
                    intent.putExtra("mail",data.getDetails());


                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });

        }


        @Override
        public int getItemCount() {
            return listData.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imgUser;
            TextView tvName, tvEmail,id;
            ProgressBar progress;

            public MyViewHolder(View view) {
                super(view);
//                imgUser = view.findViewById(R.id.imgUser);
//                id = view.findViewById(R.id.id);
//                tvName = view.findViewById(R.id.tvName);
                tvEmail = view.findViewById(R.id.tvEmail);
//                progress = view.findViewById(R.id.progress);
            }
        }
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Users");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}