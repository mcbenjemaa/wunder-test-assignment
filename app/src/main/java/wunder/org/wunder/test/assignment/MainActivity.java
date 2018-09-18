package wunder.org.wunder.test.assignment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.google.android.gms.maps.model.Marker;
import com.zuluft.safeFragmentTransaction.SafeFragmentTransaction;

import java.io.IOException;
import java.util.HashMap;


import butterknife.BindView;
import butterknife.ButterKnife;

import wunder.org.wunder.test.assignment.model.PlacemarksItem;
import wunder.org.wunder.test.assignment.service.CarService;
import wunder.org.wunder.test.assignment.ui.CarsFragment;
import wunder.org.wunder.test.assignment.ui.MapFragment;
import wunder.org.wunder.test.assignment.ui.MoreFragment;



public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_bar)
    BottomNavigationBar bottomBar;

    @BindView(R.id.container)
    FrameLayout container;


    private SafeFragmentTransaction mSafeFragmentTransaction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mSafeFragmentTransaction = SafeFragmentTransaction.createInstance(getLifecycle(),
                getSupportFragmentManager());
        getLifecycle().addObserver(mSafeFragmentTransaction);




        setupBottomBar();

        addFragment(new CarsFragment(),"Cars");
        bottomBar.selectTab(0,true);

        getLocations(CarService.getInstance(this));
    }



    public final SafeFragmentTransaction getSafeFragmentTransaction() {
        return mSafeFragmentTransaction;
    }


    public void    addFragment(Fragment f, String tag){

        getSafeFragmentTransaction().registerFragmentTransition(
                fragmentManager ->
                {
                    fragmentManager.popBackStackImmediate();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, f, tag)
                            .commit();});

    }




    public void setupBottomBar(){

        BottomBarItem item = new BottomBarItem(R.drawable.car, R.string.carlist);

        bottomBar.addTab(item);

        BottomBarItem item2 = new BottomBarItem(R.drawable.map, R.string.carpin);

        bottomBar.addTab(item2);

        BottomBarItem item3 = new BottomBarItem(R.drawable.more, R.string.more);

        bottomBar.addTab(item3);




        bottomBar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                navigate(position);
            }
        });










    }



    public void getLocations(CarService carService){

        Log.i("cars",carService.getLocations().toString());

    }

    private void navigate(int position) {


        switch (position){

            case 0:  addFragment(new CarsFragment(),"Cars");
                break;
            case 1: addFragment(new MapFragment(),"Map");
                break;
            case 2: addFragment(new MoreFragment(),"More");
                break;

            default: addFragment(new CarsFragment(),"Cars");

        }

    }

    public void selectFragment(Fragment fragment,String tag,int pos)
    {
        addFragment(fragment,tag);
        bottomBar.selectTab(pos,true);
    }

}
