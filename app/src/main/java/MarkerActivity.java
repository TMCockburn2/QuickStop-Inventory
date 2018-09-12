import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.tyler.shoppinglistapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Tyler on 11/10/2017.
 */

public class MarkerActivity  extends FragmentActivity implements
        GoogleMap.OnMarkerClickListener,
        OnMapReadyCallback {
    private static final LatLng gboroShoprite = new LatLng(39.7192878, -75.10949189999997);
    private Marker gShopMarker;
    private GoogleMap mMap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gShopMarker = mMap.addMarker(new MarkerOptions().position(gboroShoprite).title("Glassboro Shoprite"));
        gShopMarker.setTag(0);
        mMap.setOnMarkerClickListener(this);

    }

    /**
     * Called when the user clicks a marker.
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        return false;
    }
}
