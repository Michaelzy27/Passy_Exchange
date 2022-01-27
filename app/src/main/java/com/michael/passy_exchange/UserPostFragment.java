package com.michael.passy_exchange;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class UserPostFragment extends Fragment {

    LinearLayoutManager recyclerLayout;


    public static UserPostFragment newInstance() {
        return new UserPostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_post_fragment, container, false);

        RecyclerView pendingOrders = root.findViewById(R.id.pending_orders);
        recyclerLayout = new LinearLayoutManager(getContext());

        pendingOrders.setLayoutManager(recyclerLayout);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel

    }

}