package com.michael.hng_board.ui.UserPost;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michael.hng_board.Adapters.NotificationAdapter;
import com.michael.hng_board.Adapters.UserPostAdapter;
import com.michael.hng_board.Models.UserPostModel;
import com.michael.hng_board.R;
import com.michael.hng_board.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class UserPostFragment extends Fragment {

    private UserPostViewModel mViewModel;
    private RecyclerView recyclerView;
    private Helper helper;
    private UserPostAdapter userPostAdapter;

    public static UserPostFragment newInstance() {
        return new UserPostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_post_fragment, container, false);
        helper = new Helper(getContext());

        recyclerView = root.findViewById(R.id.post_recyclerView);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        userPostAdapter = new UserPostAdapter(getContext(), userPostLocalData());
        recyclerView.setAdapter(userPostAdapter);



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserPostViewModel.class);
        // TODO: Use the ViewModel

    }

    public List<UserPostModel> userPostLocalData(){
        List<UserPostModel> userPostListData = new ArrayList<>();

        String s2 = "Hello, I am John Doe, happy to be here";
        UserPostModel userPostModel = new UserPostModel("default", "John Doe", "25min", "Introduction", "Hello, I am John Doe, happy to be here");
        userPostListData.add(userPostModel);
        userPostListData.add(userPostModel);
        userPostListData.add(userPostModel);

        return userPostListData;
    }

}