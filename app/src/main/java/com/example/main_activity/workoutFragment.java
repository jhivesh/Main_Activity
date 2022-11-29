package com.example.main_activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class workoutFragment extends Fragment {

    RecyclerView recView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // View view = inflater.inflate(R.layout.fragment_workout, container,false);
        //recyclerview= view.findViewById(R.id.workout_recycle_id);

        //RecyclerView workoutRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_workout, container, false);
        //workoutRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        View view = inflater.inflate(R.layout.fragment_workout, container,false);
        recView = view.findViewById(R.id.workout_recycle_id);



        String[] workouts = new String[Workout.workouts.length];
        for (int i = 0; i < workouts.length; i++) {
            workouts[i] = Workout.workouts[i].getName();
        }

        int[] workoutImages = new int[Workout.workouts.length];
        for (int i = 0; i < workoutImages.length; i++) {
            workoutImages[i] = Workout.workouts[i].getImageResourceID();
        }

        String [] workoutDescription = new String[Workout.workouts.length];
        for (int i = 0; i < workoutImages.length; i++) {
            workoutDescription[i] = Workout.workouts[i].getDescription();
        }


        CardWorkoutAdapter adapter = new CardWorkoutAdapter(workouts,workoutImages,workoutDescription);
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