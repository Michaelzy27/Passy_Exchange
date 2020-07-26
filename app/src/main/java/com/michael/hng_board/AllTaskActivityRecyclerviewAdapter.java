package com.michael.hng_board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AllTaskActivityRecyclerviewAdapter extends RecyclerView.Adapter<AllTaskActivityRecyclerviewAdapter.AllTaskViewHolder> {

    List<AllTaskTestDataClass> taskArray = new ArrayList<>();

    @NonNull
    @Override
    public AllTaskActivityRecyclerviewAdapter.AllTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View taskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_task_recyclerview, parent, false);
        AllTaskViewHolder allTaskViewHolder = new AllTaskViewHolder(taskView);
        return allTaskViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AllTaskActivityRecyclerviewAdapter.AllTaskViewHolder holder, int position) {

        AllTaskTestDataClass allTaskTestDataClass = taskArray.get(position);
        holder.setTask(allTaskTestDataClass.getTask());
        holder.setDate(allTaskTestDataClass.getDate());
        holder.setTime(allTaskTestDataClass.getTime());

    }

    @Override
    public int getItemCount() {
        return taskArray.size();
    }

    public void addTask(AllTaskTestDataClass allTaskTestDataClass){
        taskArray.add(allTaskTestDataClass);
        notifyItemInserted(taskArray.size() - 1);
    }


    public class AllTaskViewHolder extends RecyclerView.ViewHolder {

        private TextView task, date, time;

        public void setTask(String task) {
            this.task.setText(task);
        }

        public void setDate(String date) {
            this.date.setText(date);
        }

        public void setTime(String time) {
            this.time.setText(time);
        }

        public AllTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.all_task);
            date = itemView.findViewById(R.id.all_task_date);
            time = itemView.findViewById(R.id.all_task_time);
        }
    }
}
