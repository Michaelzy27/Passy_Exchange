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
import com.michael.hng_board.R;
import com.michael.hng_board.Utils.Helper;

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

        recyclerView = root.findViewById(R.id.notification_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        userPostAdapter = new NotificationAdapter(getContext(), helper.notificationLocalData());
        recyclerView.setAdapter(userPostAdapter);



        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserPostViewModel.class);
        // TODO: Use the ViewModel
    }

}