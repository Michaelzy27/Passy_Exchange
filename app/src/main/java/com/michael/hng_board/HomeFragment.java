package com.michael.hng_board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.michael.hng_board.homepage.Task;
import com.michael.hng_board.homepage.TaskAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView taskRecycler;
    TaskAdapter taskAdapter;
    List<Task> tasks = new ArrayList<>();
    TextView WelcomeText;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        taskRecycler = view.findViewById(R.id.tasks_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        taskRecycler.setLayoutManager(layoutManager);
        taskAdapter = new TaskAdapter(tasks);
        taskRecycler.setAdapter(taskAdapter);
        WelcomeText = view.findViewById(R.id.welcome_text);

        getUserProfile getUserProfile = new getUserProfile();
        getUserProfile.execute();

        prepareTasks();

        return view;

    }
    private void prepareTasks() {
        Task task = new Task("Stage 2 entry Task: Hotel website design","Deadline: 31/07/2020 12:00 AM",
                "Design","pending",R.drawable.ic_baseline_arrow_forward_ios_24);
        tasks.add(task);
        task = new Task("Task 0.5: Git","Deadline: 07/06/2020 12:00 AM",
                "General","closed",R.drawable.ic_baseline_arrow_forward_ios_24);
        tasks.add(task);
        task = new Task("Task 0.5: Lucid","Deadline: 07/06/2020 12:00 AM",
                "General","closed",R.drawable.ic_baseline_arrow_forward_ios_24);
        tasks.add(task);
    }

    public class getUserProfile extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient okHttpClient = new OkHttpClient();

            SharedPreferences sharedP = getContext().getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
            String token = sharedP.getString("token", "Token");
            String userId = sharedP.getString("userId", "UserId");
            String LasttName = "";
            String aasttName = "";
            String dataResult = "";
            Log.i("token", token);
            Log.i("userid", userId);

            Request request = new Request.Builder()
                    .url("https://hngboard.herokuapp.com/users/" + userId + "/profile")
                    .header("Authorization", "Bearer "  + token )
                    .build();

            try {
                Response response = okHttpClient.newCall(request).execute();
                String responsebody = response.body().string();
                Log.i("userRBody", responsebody);

                JSONObject jsonObject = new JSONObject(responsebody);
                dataResult = jsonObject.getString("data");

                JSONObject dataObject = new JSONObject(dataResult);
                String LastName = dataObject.getString("lastName");

               // SharedPreferences.Editor editor = sharedP.edit();
               // editor.putString("lastName", LastName);

            }catch (Exception e){
                e.printStackTrace();
            }


            return dataResult;
        }

        @Override
        protected void onPostExecute(String dataResult) {

            try {
                JSONObject dataObject = new JSONObject(dataResult);
                String LasttName = dataObject.getString("lastName");
                String FirstName = dataObject.getString("firstName");
                String HngID = dataObject.getString("hngId");

                WelcomeText.setText("Welcome " + LasttName );


            }catch (Exception e){
                e.printStackTrace();
            }


            super.onPostExecute(dataResult);
        }
    }

}