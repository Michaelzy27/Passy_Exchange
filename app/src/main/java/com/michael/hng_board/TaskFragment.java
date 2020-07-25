package com.michael.hng_board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerview;
    private LinearLayoutManager layoutManager;
    private AllTaskActivityRecyclerviewAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance(String param1, String param2) {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = (inflater.inflate(R.layout.fragment_task, container, false));

        recyclerview = root.findViewById(R.id.all_task_recyclerview);
        layoutManager = new LinearLayoutManager(this.getContext());
        adapter = new AllTaskActivityRecyclerviewAdapter();

        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(layoutManager);

        adapter.addTask(new AllTaskTestDataClass("Stage 2 Entry Task: Hotel Website", "04/06/20", "13:45 PM"));
        adapter.addTask(new AllTaskTestDataClass("Task 0.5: Git", "09/07/20", "20:16 PM"));
        adapter.addTask(new AllTaskTestDataClass("Task 0.5: Lucid", "23/07/20", "01:13 AM"));

        return root;
    }
}