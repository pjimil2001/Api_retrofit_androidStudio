package com.example.apidemo.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.example.apidemo.R;
import com.example.apidemo.api.APIClient;
import com.example.apidemo.api.APIInterface;
import com.example.apidemo.api.NoConnectivityException;
import com.example.apidemo.models.episodedetails;
import com.example.apidemo.models.episoderesponse;
import com.example.apidemo.utility.Utility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_ep extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter adapter;
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

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity_ep.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUser();
            }
        });

        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
                getUser();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getUser();
    }

    public void getUser() {
        llProgressBar.setVisibility(View.VISIBLE);
        APIClient
                .getClient(MainActivity_ep.this)
                .create(APIInterface.class)
                .getUser()
                .enqueue(new Callback<episoderesponse>() {
                    @Override
                    public void onResponse(Call<episoderesponse> call, Response<episoderesponse> response) {
                        llProgressBar.setVisibility(View.GONE);
                        if (swipeRefresh.isRefreshing())
                            swipeRefresh.setRefreshing(false);
                        if (response.code() == 200) {
                            if (response.body() != null) {
                                episoderesponse apiResponse = response.body();

                                if (apiResponse.getResult_all_episode().size() > 0) {
                                    adapter = new UserAdapter(MainActivity_ep.this, apiResponse.getResult_all_episode());
                                    recyclerView.setAdapter(adapter);
                                    if (apiResponse.getResult_all_episode().size() > 0) {
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
                            Utility.showError(MainActivity_ep.this, response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<episoderesponse> call, Throwable t) {
                        llProgressBar.setVisibility(View.GONE);
                        if (swipeRefresh.isRefreshing())
                            swipeRefresh.setRefreshing(false);
                        Log.e("onFailure", t.toString() + "");
                        if (t instanceof NoConnectivityException) {
                            Toast.makeText(MainActivity_ep.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        call.cancel();
                    }
                });
    }

    class UserAdapter extends RecyclerView.Adapter<MainActivity_ep.UserAdapter.MyViewHolder> {

        private List<episodedetails> listData;
        Activity context;

        public UserAdapter(Activity context, List<episodedetails> listData) {
            this.context = context;
            this.listData = listData;
        }

        @Override
        public MainActivity_ep.UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MainActivity_ep.UserAdapter.MyViewHolder holder, int position) {

            episodedetails  data = listData.get(position);

//            new GlideImageLoader(holder.imgUser, holder.progress).load(data.getAvatar(), Utility.getGlideRequestOptions());


//            holder.tvName.setText(data.getFirst_name() + "    " + data.getLast_name());
            holder.tvEmail.setText(data.getTitle());
            holder.id.setText(  data.getId());

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

                    intent.putExtra("id",data.getId());
//                    intent.putExtra("img",data.getAva tar());
//                    intent.putExtra("first",data.getFirst_name());
//                    intent.putExtra("last",data.getLast_name());
                    intent.putExtra("mail",data.getTitle());


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
                id = view.findViewById(R.id.id);
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