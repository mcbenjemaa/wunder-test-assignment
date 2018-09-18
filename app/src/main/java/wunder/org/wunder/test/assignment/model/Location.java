package wunder.org.wunder.test.assignment.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("placemarks")
	private List<PlacemarksItem> placemarks;

	public void setPlacemarks(List<PlacemarksItem> placemarks){
		this.placemarks = placemarks;
	}

	public List<PlacemarksItem> getPlacemarks(){
		return placemarks;
	}

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"placemarks = '" + placemarks + '\'' + 
			"}";
		}
}