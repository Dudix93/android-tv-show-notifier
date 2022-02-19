package com.example.android_tv_show_notifier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoModel> todosArrayList;
    private RecyclerView todosRecyclerView;
    private RecyclerView.Adapter todosListAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        TodoAPI todoAPI = new RetrofitInstance().api;

        Call<List<TodoModel>> call = todoAPI.getTodos();

        call.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {

                if (response.code() != 200) {

                }
                else {
                    if (response.body() != null) {
                        todosArrayList = new ArrayList<TodoModel>(response.body());
                        todosRecyclerView = (RecyclerView) findViewById(R.id.todos);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                        todosRecyclerView.setLayoutManager(mLayoutManager);
                        todosListAdapter = new TodosListAdapter(todosArrayList);
                        todosRecyclerView.setAdapter(todosListAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {

            }
        });
    }
}