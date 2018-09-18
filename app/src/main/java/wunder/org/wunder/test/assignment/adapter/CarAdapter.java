package wunder.org.wunder.test.assignment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wunder.org.wunder.test.assignment.MainActivity;
import wunder.org.wunder.test.assignment.R;
import wunder.org.wunder.test.assignment.model.PlacemarksItem;
import wunder.org.wunder.test.assignment.ui.CarsFragment;
import wunder.org.wunder.test.assignment.ui.MapFragment;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarVH> {

    List<PlacemarksItem> mdata;

    Context context;
    public CarAdapter(Context context,List<PlacemarksItem> mdata){

        this.context=context;
        this.mdata=mdata;
    }


    @NonNull
    @Override
    public CarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item,parent,false);

        return new CarVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarVH holder, int position) {
        PlacemarksItem p=mdata.get(position);


        holder.tv_adresse.setText(p.getAddress());
        holder.tv_carName.setText(p.getName());
        holder.tv_engine.setText("Engine Type: "+p.getEngineType());
        holder.tv_interior.setText("Interior: "+p.getInterior());
        holder.tv_exterior.setText("Exterior: "+p.getExterior());
        holder.tv_fuel.setText("Fuel: "+p.getFuel());
        holder.tv_vin.setText("Vin: "+p.getVin());


        holder.carView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle b=new Bundle();
                Gson gson=new Gson();
                b.putString("car",gson.toJson(p));
                MapFragment fragment=new MapFragment();
                fragment.setArguments(b);
                ((MainActivity)context).selectFragment(fragment,"Map",1);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class CarVH extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_adress)
        TextView tv_adresse;
        @BindView(R.id.tv_carName)
        TextView tv_carName;
        @BindView(R.id.tv_engine)
        TextView tv_engine;
        @BindView(R.id.tv_exterior)
        TextView tv_exterior;
        @BindView(R.id.tv_interior)
        TextView tv_interior;
        @BindView(R.id.tv_vin)
        TextView tv_vin;
        @BindView(R.id.tv_fuel)
        TextView tv_fuel;
        @BindView(R.id.carView)
        View carView;



        public CarVH(View v){
            super(v);
            ButterKnife.bind(this,v);
        }
    }
}
