package wunder.org.wunder.test.assignment.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wunder.org.wunder.test.assignment.R;
import wunder.org.wunder.test.assignment.adapter.CarAdapter;
import wunder.org.wunder.test.assignment.model.Location;
import wunder.org.wunder.test.assignment.model.PlacemarksItem;
import wunder.org.wunder.test.assignment.service.CarService;

public class CarsFragment extends Fragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    CarAdapter carAdapter;

    List<PlacemarksItem> mdata;


    public CarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_cars, container, false);
        ButterKnife.bind(this,v);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mdata=new ArrayList<>();
        carAdapter=new CarAdapter(getActivity(),mdata);
        recyclerView.setAdapter(carAdapter);

        loadCars();

        return v;
    }


    public void loadCars(){

        Location location = CarService.getInstance(getActivity()).getLocations();

        mdata.clear();
        mdata.addAll(location.getPlacemarks());
        carAdapter.notifyDataSetChanged();


    }

}
