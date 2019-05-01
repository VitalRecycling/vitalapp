package com.example.android.vitalrecycling;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap2;
    private Button mCashbutton;
    private Button mrecycleButton;
    LatLng bin_one = new LatLng(27.525926, -97.878531);
    LatLng bin_two = new LatLng(27.524499, -97.879035);
    LatLng bin_three = new LatLng(27.527272, -97.879768);
    LatLng bin_four = new LatLng(27.527760, -97.881515);
    LatLng bin_five = new LatLng(27.526970, -97.881357);
    LatLng bin_six = new LatLng(27.524564, -97.881351);
    LatLng bin_seven = new LatLng(27.523920,-97.880973);
    LatLng bin_eaight = new LatLng(27.523194,-97.881051);
    LatLng bin_nine = new LatLng(27.525643,-97.883945);
    LatLng bin_ten = new LatLng(27.523028,-97.882127);
    LatLng bin_eleven = new LatLng(27.525643,-97.883945);
    LatLng bin_thirteen = new LatLng(27.523969, -97.883478);
    LatLng bin_fourteen = new LatLng(27.523935, -97.885571);
    LatLng bin_fifteeen = new LatLng(27.527876, -97.885373);
    LatLng tamuk = new LatLng(27.525219, -97.882426);
    LatLng building= new LatLng(27.525836, -97.880768);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        mrecycleButton = findViewById(R.id.DonationButton);

        mrecycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap2.clear();
                mMap2.addMarker(new MarkerOptions().position(tamuk).title("Texas A&M University Kingsville"));
            }
        });

        mCashbutton = findViewById(R.id.BinButton);

        mCashbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap2.clear();
                mMap2.addMarker(new MarkerOptions().position(bin_one).title("BIN 1"));
                mMap2.addMarker(new MarkerOptions().position(bin_two).title("BIN 2"));
                mMap2.addMarker(new MarkerOptions().position(bin_three).title("BIN 3"));
                mMap2.addMarker(new MarkerOptions().position(bin_four).title("BIN 4"));
                mMap2.addMarker(new MarkerOptions().position(bin_five).title("BIN 5"));
                mMap2.addMarker(new MarkerOptions().position(bin_six).title("BIN 6"));
                mMap2.addMarker(new MarkerOptions().position(bin_seven).title("BIN 7"));
                mMap2.addMarker(new MarkerOptions().position(bin_eaight).title("BIN 8"));
                mMap2.addMarker(new MarkerOptions().position(bin_nine).title("BIN 9"));
                mMap2.addMarker(new MarkerOptions().position(bin_ten).title("BIN 10"));
                mMap2.addMarker(new MarkerOptions().position(bin_eleven).title("BIN 11"));
                mMap2.addMarker(new MarkerOptions().position(building).title("BIN 13"));
                mMap2.addMarker(new MarkerOptions().position(bin_thirteen).title("BIN 14"));
                mMap2.addMarker(new MarkerOptions().position(bin_fourteen).title("BIN 15"));
                mMap2.addMarker(new MarkerOptions().position(bin_fifteeen).title("BIN 16"));


            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap2 = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);





        LatLngBounds KINGSVILLE = new LatLngBounds(new LatLng(27.496436, -97.899486), new LatLng(27.805159, -97.305647));
        mMap2.moveCamera(CameraUpdateFactory.newLatLngZoom(tamuk, 16));
        mMap2.setLatLngBoundsForCameraTarget(KINGSVILLE);

    }
}
