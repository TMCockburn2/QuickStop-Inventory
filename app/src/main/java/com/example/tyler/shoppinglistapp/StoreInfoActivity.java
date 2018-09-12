package com.example.tyler.shoppinglistapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Tyler on 12/12/2017.
 *
 * This class will need to contain the information for the specific store, as well as load the database for that information.
 */

public class StoreInfoActivity extends AppCompatActivity {

    //private GetNearbyPlacesData getNearbyPlacesData;
    private Button searchButton;
    private EditText search;
    private TextView result;
    private TextView storeName;
    private String JSONstring;
    private StringBuilder itemNames = new StringBuilder();



    //constructor requires an instance of getNearbyPlacesData so it will have the data needed to create
    //amount of buttons based on locations in the area



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_info);
        searchButton = (Button)findViewById(R.id.button4);
        search = (EditText)findViewById(R.id.edit1);
        //result = (TextView)findViewById(R.id.resultText);
        storeName = (TextView)findViewById(R.id.storeName);
        Intent intent = getIntent();
        String value = intent.getStringExtra("storeName");
        storeName.setText(value);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        /**
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String item = search.getText().toString();



                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    //connects to the specific server
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://192.168.0.126:3306/RetailInfo","root","Cockburn");
                    Statement stmt=con.createStatement();
                    //this line should extract/show all items in the table that have the itemID/name that was entered in the search box
                    //prior to searching
                    /**Will need to make the search more specific because this will currently just pick
                     * up eggs for example, not egglands best eggs. Need to check if the names contain eggs or something
                     * ***COMPLETED***/

                    /**
                     * Will need to adjust the executeQuery line. It probably can be done so one line will call what is currently stated, and also
                     * check the storename/storeid.
                     */
/**
                    ResultSet rs=stmt.executeQuery("SELECT ItemID FROM ItemDetails WHERE ItemgenericID = " + item);
                    //Displays the item infomration collected
                    int count = 1;
                    while(rs.next()){

                        String itemId = rs.getString("ItemID");
                        String aisle = rs.getString("ItemLocAisle");
                        String shelf = rs.getString("ItemLocShelf");
                        itemNames.append(itemId + "\n");
                        //make this in a scroll box
                        //System.out.print(count + ") " + itemId + ": Aisle " + aisle + ", shelf " + shelf + "\n");
                        count++;
                    }
                }catch(Exception e){
                    System.out.println(e);

                }



                /**
                 * this space will be used to display the contents of what is searched for from the textbox. Using the item variable,
                 * we can display only what is in the database with that as the filter; then display it in the label in a manner such as
                 * setText("Item: " + item + ": Aisle " + (database info))
                 */
/**
                result.setText(itemNames);


            }
        });


**/

    }
    public void getJSON(View view){
        new BackgroudTask().execute();
    }
        class BackgroudTask extends AsyncTask<Void,Void,String> {
            String jsonURL;

            @Override
            protected void onPreExecute() {
                jsonURL = "https://tcockburnapp.000webhostapp.com/";
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(jsonURL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader b = new BufferedReader(new InputStreamReader((inputStream)));
                    StringBuilder stringBuilder = new StringBuilder();
                    while((JSONstring = b.readLine())!= null){
                        stringBuilder.append(JSONstring + "\n");
                    }
                    b.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return stringBuilder.toString().trim();

                }
                catch(MalformedURLException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }

                    return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                TextView jsonResult = (TextView)findViewById(R.id.resultText);
                jsonResult.setText(result);
            }

        }



    /**
     * This class will need to populate buttons and/or checkboxes based on the amount of valid locations in the
     * radius determined by the user. After locations are selected, there would need to be some sort
     * of outside access to shopping center databases to use the information
     */


}
