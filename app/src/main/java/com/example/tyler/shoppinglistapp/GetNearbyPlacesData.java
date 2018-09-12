package com.example.tyler.shoppinglistapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tyler on 11/10/2017.
 */

public class GetNearbyPlacesData extends AsyncTask<Object, String, String>{

    String googlePlacesData;
    GoogleMap mMap;
    String url;
    ArrayList <Locations> mapLocations = new ArrayList<>();
    ArrayList<String>stores = new ArrayList<>();
    Locations locationName;
    int placeAmount;
    String t;
    String addressNames;
    StringBuilder ad = new StringBuilder();
    protected MapsActivity context;
    int count = 0;


    public GetNearbyPlacesData(Context context){
        this.context = (MapsActivity)context;
    }
    @Override
    public String doInBackground(Object... params) {
        try {
            Log.d("GetNearbyPlacesData", "doInBackground entered");
            mMap = (GoogleMap) params[0];
            url = (String) params[1];
            DownloadUrl downloadUrl = new DownloadUrl();
            googlePlacesData = downloadUrl.readUrl(url);
            Log.d("GooglePlacesReadTask", "doInBackground Exit");
        } catch (Exception e) {
            Log.d("GooglePlacesReadTask", e.toString());
        }
        return googlePlacesData;
    }

    @Override
    public void onPostExecute(String result) {
        Log.d("GooglePlacesReadTask", "onPostExecute Entered");
        List<HashMap<String, String>> nearbyPlacesList = null;
        DataParser dataParser = new DataParser();
        nearbyPlacesList =  dataParser.parse(result);
        ShowNearbyPlaces(nearbyPlacesList);
        t = "Toast";
        //ad = addressNames(nearbyPlacesList);
        //Log.d("GooglePlacesReadTask", "onPostExecute Exit");
    }




    public void ShowNearbyPlaces(final List<HashMap<String, String>> nearbyPlacesList) {
        locationName = new Locations();
        addressNames = "";
        for (int i = 0; i < nearbyPlacesList.size(); i++) {
            Log.d("onPostExecute","Entered into showing locations");
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : " + vicinity);
            mMap.addMarker(markerOptions);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
            locationName.setLocationName(placeName);
            mapLocations.add(locationName);
            stores.add(placeName);
            ad.append(placeName + "\n");
            System.out.println(markerOptions.getTitle() +" bob");
            final Button myButton = new Button(context);
            myButton.setText(stores.get(i));
            myButton.setId(i);
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * This will be where the button click will bring the app to a new intent where one can
                     * search for items with the specific store if there is a database for it
                     */
                    //Test string
                    Intent myIntent = new Intent(context, StoreInfoActivity.class);
                    myIntent.putExtra("storeName", myButton.getText()); //Optional parameters
                    context.startActivity(myIntent);
                    //myButton.setText("Testing string" + myButton.getId());

                }
            });
            context.buttons.add(myButton);

            //context.lin.addView(myButton);




        }
        for (int j = 0; j < stores.size(); j++){
            context.lin.addView(context.buttons.get(j));
        }

    }


    public void setList(StringBuilder ad){
        this.ad = ad;
    }
    public StringBuilder getList(){
        return ad;
    }





    public StringBuilder addressNames(List<HashMap<String, String>> nearbyPlacesList){
        locationName = new Locations();
        addressNames = "";
        for (int i = 0; i < nearbyPlacesList.size(); i++) {
            HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat, lng);

        }
        return ad;
    }

    //variable getter/setter so we have the amount of locations that are calculated as a number. That
    //way we can easily create the amount of buttons/checkboxes in the screen that shows all of the
    //store options

    public int getPlaceAmount(){
        return placeAmount;
    }

    public void setPlaceAmount(int placeAmount){
        this.placeAmount = placeAmount;
    }
    public String toString(){
        String strOutput = "MyArrayListObject [";  //Prints constructor name + left bracket
        for(int i = 0; i < mapLocations.size(); i++){     //myArr = ArrayList instance variable
            strOutput += mapLocations.get(i) + ", ";
        }
        strOutput = strOutput + "]";
        return strOutput;
    }

}