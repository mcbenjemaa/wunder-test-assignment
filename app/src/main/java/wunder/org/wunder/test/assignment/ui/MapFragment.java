package wunder.org.wunder.test.assignment.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wunder.org.wunder.test.assignment.R;
import wunder.org.wunder.test.assignment.model.Location;
import wunder.org.wunder.test.assignment.model.PlacemarksItem;
import wunder.org.wunder.test.assignment.service.CarService;

public class MapFragment extends Fragment {


    List<PlacemarksItem> cars;

    HashMap<PlacemarksItem, Marker> hashMap;


    PlacemarksItem car;

    @BindView(R.id.mapView)
    MapView mMapView;
    private GoogleMap googleMap;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this,v);

        initMap(savedInstanceState);

        if(getArguments()!=null){

            Gson gson=new Gson();
            car=gson.fromJson(getArguments().getString("car"),PlacemarksItem.class);


        }


        // Inflate the layout for this fragment
        return v;
    }


    public void initMap( Bundle savedInstanceState){
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                loadCars();
                // For showing a move to my location button


                // For dropping a marker at a point on the Map
               /* LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                // For zooming automatically to the location of the marker

                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/


            }
        });

    }


    public void loadCars(){

        Location location = CarService.getInstance(getActivity()).getLocations();

        cars=new ArrayList<>();
        cars.addAll(location.getPlacemarks());

        pinCars();



    }

    public void pinCars(){

        hashMap=new HashMap<>();

        for(PlacemarksItem c : cars){

            addPin(c);
        }



        //Move the Camera to the first Car Pin or to the selected Car in the list

        if(car!=null){
            zoom(car,16);
            hashMap.get(car).showInfoWindow();

        }else{
            zoom(cars.get(0),12);
        }


    }


    public void zoom(PlacemarksItem car,int zoom){

        LatLng latLng=new LatLng(car.getCoordinates().get(0),car.getCoordinates().get(1));


        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(zoom).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

       
    }

    public void addPin(PlacemarksItem car){

        LatLng latLng=new LatLng(car.getCoordinates().get(0),car.getCoordinates().get(1));

        Marker marker=googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(car.getName())
                .snippet(car.getAddress())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        hashMap.put(car,marker);


    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
