package com.example.android_tv_show_notifier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tv_show_notifier.databinding.ItemTodoBinding;

import java.util.ArrayList;

public class TodosListAdapter extends RecyclerView.Adapter<TodosListAdapter.TodoViewHolder> {

    private ArrayList<TodoModel> mTodos = new ArrayList<TodoModel>();

    public TodosListAdapter(ArrayList<TodoModel> todos) {
        this.mTodos = todos;
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public TodoViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.title);
        }

        public TextView getTitleTextView() {
            return this.titleTextView;
        }
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viwType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_todo, viewGroup, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder viewHolder, final int position) {
        viewHolder.getTitleTextView().setText(this.mTodos.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return this.mTodos.size();
    }
}
