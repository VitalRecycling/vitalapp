package com.example.android.vitalrecycling;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private  GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private  Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code= 99;
    double latitude, longitude;
    private int proximityRadius= 10000;
    LatLng Kingsville = new LatLng(27.525115, -97.882475);
    LatLng bin_one = new LatLng(27.497832, -97.857057);
    LatLng bin_two = new LatLng(27.509676, -97.856515);
    LatLng bin_three = new LatLng(27.515806, -97.841652);
    LatLng bin_four = new LatLng(27.516869, -97.869654);
    LatLng bin_five = new LatLng(27.531051, -97.869078);
    LatLng goodwill= new LatLng(27.500843, -97.843133);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocation();
        }



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onClick(View v)
    {
//        String hospital= "hospital", RecycleCenters= "Recycle Centers";
        Object transferData[]= new Object[2];
        GetNearByPlaces getNearByPlaces= new GetNearByPlaces();



        switch (v.getId())
        {
            case R.id.Serching_address:
                EditText addressField = (EditText) findViewById(R.id.location_Search);
                String address = addressField.getText().toString();

                List<Address> addressList=null;
                MarkerOptions userMarkerOptions = new MarkerOptions();

                if (!TextUtils.isEmpty(address))
                {
                    Geocoder geocoder= new Geocoder(this);
                    try {
                        addressList = geocoder.getFromLocationName(address, 6);

                        if(addressList !=null){
                            for (int i =0; i<addressList.size(); i++ )
                            {
                                Address userAddress =addressList.get(i);
                                LatLng latLng= new LatLng(userAddress.getLatitude(), userAddress.getLongitude());

                                userMarkerOptions.position(latLng);
                                userMarkerOptions.title(address);
                                userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                                mMap.addMarker(userMarkerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(8));


                            }
                        }
                        else
                        {
                            Toast.makeText(this, "Location not found...", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(this, "Please write any location name", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.CashButton:
                mMap.clear();
                String School= "scrap yards";
                String url= getUrl(latitude, longitude, School);
                transferData[0]=mMap;
                transferData[1]=url;




                getNearByPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearest recycling centers", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing nearest recycling centers", Toast.LENGTH_SHORT).show();
                break;

            case R.id.recycleButton:
                mMap.clear();
                String hospital= "recycle centers";
                url= getUrl(latitude, longitude, hospital);
                transferData[0]=mMap;
                transferData[1]=url;


                mMap.addMarker(new MarkerOptions().position(bin_one).title("CLOTHS DONATION BOX").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.addMarker(new MarkerOptions().position(bin_two).title("CLOTHS DONATION BOX").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.addMarker(new MarkerOptions().position(bin_three).title("CLOTHS DONATION BOX").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.addMarker(new MarkerOptions().position(bin_four).title("CLOTHS DONATION BOX").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.addMarker(new MarkerOptions().position(bin_five).title("CLOTHS DONATION BOX").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                mMap.addMarker(new MarkerOptions().position(goodwill).title("GOODWILL").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


                getNearByPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearest recycling centers", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing nearest recycling centers", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private String getUrl(double latitude, double longitude,String nearbyPlace)
    {
        StringBuilder googleURL=new StringBuilder("\n" +
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location=" +  latitude+ "," + longitude);
        googleURL.append("&radius="+ proximityRadius);
        googleURL.append("&type=recycling");
        googleURL.append("&keyword="+ nearbyPlace);
        googleURL.append("&key=" + "AIzaSyBTflkxOnr_ldwRt2u1in7VDIYzlAftwEc");

        Log.d("GoogleMapsActivity", "url="+ googleURL.toString());

        return googleURL.toString();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kingsville, 6));

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){

            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }
    }


    public boolean checkUserLocation()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED )
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }

            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);

            }
            return false;
        }
        else{
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient==null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else {
                    Toast.makeText(this,"not working", Toast.LENGTH_SHORT).show();
                }
                return;
        }


    }





    protected synchronized void buildGoogleApiClient(){
        googleApiClient= new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();


    }



    @Override
    public void onLocationChanged(Location location)
    {
        latitude=location.getLatitude();
        longitude=location.getLongitude();

        lastLocation= location;
        if (currentUserLocationMarker!= null)
        {
            currentUserLocationMarker.remove();
        }

        LatLng latLng= new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions= new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("User Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        currentUserLocationMarker= mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(9));

        if (googleApiClient !=null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest=new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
