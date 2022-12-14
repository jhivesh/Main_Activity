package com.example.main_activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class workoutFragment extends Fragment {

    RecyclerView recView;DBHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_workout, container,false);
        recView = view.findViewById(R.id.workout_recycle_id);
        Workout workoutModel;

        myDB = new DBHelper(getContext());
        ArrayList<Workout> everyone = (ArrayList) myDB.getEveryone();
        Log.d("PRINT ARRAY", "size of arraylist "+ String.valueOf(everyone.size()));

        CardWorkoutAdapter adapter = new CardWorkoutAdapter(getContext(),everyone);

        recView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recView.setLayoutManager(layoutManager);

        adapter.setListener(new CardWorkoutAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), WorkoutDetailsActivity.class);
                intent.putExtra(WorkoutDetailsActivity.string_workout_id, position);
                getActivity().startActivity(intent);
            }
        });


        return recView;

    }



}