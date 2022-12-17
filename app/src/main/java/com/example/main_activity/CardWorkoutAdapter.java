package com.example.main_activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


 class CardWorkoutAdapter extends
         RecyclerView.Adapter<CardWorkoutAdapter.ViewHolder>{

    private String[] workout_Name;
    private int[] imageID;
    private String[] instruction;
    private Listener listener;

    interface Listener{
        void onClick(int position);
    }


    public static class ViewHolder extends  RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView=v;
        }
    }

    private ArrayList<Workout> mArrayWorkout;


    public CardWorkoutAdapter (Context context, ArrayList<Workout> workoutList){
        mArrayWorkout = workoutList;
    }

    @Override
    public int getItemCount()
    {return mArrayWorkout.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public CardWorkoutAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,  final int position){
        Workout workout = mArrayWorkout.get(position);
        String name = workout.getName();
        String Description = workout.getDescription();
        String imageURL = workout.getImageResourceURL();

        CardView cardView = holder.cardView;

        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        new DownloadImageTask(imageView).execute(imageURL);
        Log.d("Downloaded image:  ", imageURL);

        TextView textView = (TextView)cardView.findViewById(R.id.info_text);
        textView.setText(name);

        TextView textView1 = (TextView)cardView.findViewById(R.id.description_id);
        textView1.setText(Description);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(holder.getAdapterPosition());
                }
            }
        });



    }


}
