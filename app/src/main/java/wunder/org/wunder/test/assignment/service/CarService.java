package wunder.org.wunder.test.assignment.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;



import wunder.org.wunder.test.assignment.model.Location;

public class CarService {


    private static CarService instance;


    private Context context;


    private CarService(Context context){
     this.context=context;
    }

    public static CarService getInstance(Context context) {
        if(instance==null)
            instance=new CarService(context);

        return instance;
    }

    public Location getLocations() {


        try {
            String json = loadJSONFromAsset(context);

            if (json != null) {
                Gson gson = new Gson();

                Location location = gson.fromJson(json, Location.class);

                return location;

            }

        }catch (IOException er){
            er.printStackTrace();
        }

        return null;
    }




    private String loadJSONFromAsset(Context context) throws IOException{
        String json = null;

            InputStream is = context.getAssets().open("locations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        return json;
    }
}
