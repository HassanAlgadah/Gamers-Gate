package com.example.gamersgate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class RecAdapter extends RecyclerView.Adapter<RecAdapter.Viewholder> {
    private List<Results> resultsList;
    private List<ResultsFav> resultsfav;
    private Context con;
    private Boolean isfav = false;

    private final RecAdapterClickHandler mClickHandler;

    public interface RecAdapterClickHandler {
        void onClick(Results Results);
    }

    public RecAdapter(RecAdapterClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        con = viewGroup.getContext();
        View view = inflater.inflate(R.layout.recyclerolder, viewGroup, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        System.out.println("hassan");
        if (!isfav) {
            System.out.println("hi33");
            Results m = resultsList.get(i);
            viewholder.gamename.append(m.getName());
            if(m.getreleased()!=null) {
                viewholder.date.append(m.getreleased());
            }else{
                viewholder.date.append("unknown");
            }
            viewholder.rat.append(Double.toString(m.getRating()));
            if (m.getbackground_image() != null) {
                String im = m.getbackground_image();
                Picasso.get()
                        .load(im)
                        .into(viewholder.image);
            }
            if (m.getPlatforms() != null) {
                for (Platformm k : m.getplatforms()) {
                    viewholder.plat.append(k.getplatform().getName() + ", ");
                }
            }
        } else {
            System.out.println("hi1");
            ResultsFav m = resultsfav.get(i);
            viewholder.gamename.append(m.getName());
            viewholder.date.append(m.getReleased());
            viewholder.rat.append(Double.toString(m.getRating()));
            if (m.getBackground_image() != null) {
                String im = m.getBackground_image();
                Picasso.get()
                        .load(im)
                        .into(viewholder.image);
            }
        }
    }

    public void setResults(List<Results> Resultss) {
        resultsList = Resultss;
    }
    public void setResultsFav(List<ResultsFav> Resultss) {
        resultsfav = Resultss;
    }

    public void setIsfav(Boolean isfav) {
        this.isfav = isfav;
    }

    @Override
    public int getItemCount() {
        if (resultsList == null) {
            if(resultsfav!=null){
                return resultsfav.size();
            }
            return 0;
        }
        return resultsList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView gamename;
        public TextView date;
        public TextView rat;
        public TextView plat;
        public ImageView image;

        public Viewholder(View v) {
            super(v);
            gamename = v.findViewById(R.id.gamename);
            date = v.findViewById(R.id.date);
            rat = v.findViewById(R.id.relsee);
            plat = v.findViewById(R.id.plat);
            image = v.findViewById(R.id.gamepic);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Results m = new Results();
            if(resultsfav!=null){
                System.out.println("what is happing");
                m.setName(resultsfav.get(adapterPosition).getName());
                m.setBackground_image(resultsfav.get(adapterPosition).getBackground_image());
                m.setRating(resultsfav.get(adapterPosition).getRating());
                m.setReleased(resultsfav.get(adapterPosition).getReleased());
                m.setSlug(resultsfav.get(adapterPosition).getSlug());
                mClickHandler.onClick(m);
            }else {
                Results n = resultsList.get(adapterPosition);
                mClickHandler.onClick(n);
            }
        }
    }
}
