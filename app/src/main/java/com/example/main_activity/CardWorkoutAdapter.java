package com.example.main_activity;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

 public class CardWorkoutAdapter extends
         RecyclerView.Adapter<CardWorkoutAdapter.ViewHolder>{

    private String[] workoutname;
    private int[] imageID;
    private String[] instruction;

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView=v;
        }
    }


    public CardWorkoutAdapter (String[] workoutname, int[] imageID, String[] instruction){
        this.workoutname= workoutname;
        this.imageID=imageID;
        this.instruction=instruction;
    }

    @Override
    public int getItemCount(){
        return workoutname.length;
    }

    @Override
    public CardWorkoutAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        Drawable drawable =
                ContextCompat.getDrawable(cardView.getContext(), imageID[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(workoutname[position]);
        TextView textView = (TextView)cardView.findViewById(R.id.info_text);
        textView.setText(workoutname[position]);
    }




}
