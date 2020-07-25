package com.michael.hng_board;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AllTaskActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private LinearLayoutManager layoutManager;
    private AllTaskActivityRecyclerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_task);

        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#10507A")));
        bar.setTitle(getResources().getString(R.string.all_task));

        recyclerview = findViewById(R.id.all_task_recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new AllTaskActivityRecyclerviewAdapter();

        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(layoutManager);

        adapter.addTask(new AllTaskTestDataClass("Stage 2 Entry Task: Hotel Website", "04/06/20", "13:45 PM"));
        adapter.addTask(new AllTaskTestDataClass("Task 0.5: Git", "09/07/20", "20:16 PM"));
        adapter.addTask(new AllTaskTestDataClass("Task 0.5: Lucid", "23/07/20", "01:13 AM"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_task_appbar_icons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}