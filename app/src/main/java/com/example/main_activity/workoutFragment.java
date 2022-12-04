package com.example.main_activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
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

        //View view = inflater.inflate(R.layout.fragment_workout, container,false);
        //recyclerview= view.findViewById(R.id.workout_recycle_id);

        //RecyclerView workoutRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_workout, container, false);
        //workoutRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        View view = inflater.inflate(R.layout.fragment_workout, container,false);
        recView = view.findViewById(R.id.workout_recycle_id);

        Workout workoutModel;

        myDB = new DBHelper(getContext());
        ArrayList<Workout> everyone = (ArrayList) myDB.getEveryone();
        Log.d("PRINT ARRAY", "size of arraylist "+ String.valueOf(everyone.size()));

        //String[] workouts = myDB.getWorkouts();

        CardWorkoutAdapter adapter = new CardWorkoutAdapter(getContext(),everyone);

        // Card WorkoutAdapter adapter = new CardWorkoutAdapter();
        //workoutRecycler.setAdapter(adapter);

        recView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        //workoutRecycler.setLayoutManager(layoutManager);

        recView.setLayoutManager(layoutManager);


        return recView;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_workout, container, false);
    }



}