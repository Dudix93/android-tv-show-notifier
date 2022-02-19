package com.example.android_tv_show_notifier;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoAPI {

    @GET("/todos")
    Call<List<TodoModel>> getTodos();

//    @POST("/createTodo")
//    Response<CreateTodoResponse> createTodo(@Body todo: ToDoModel);
}
