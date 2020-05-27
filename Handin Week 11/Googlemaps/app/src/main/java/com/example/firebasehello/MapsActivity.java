package com.example.firebasehello;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback { //Callback is bcus it takes time to get the data from google

    private GoogleMap mMap;
    private String coordinates = "coordinates";
    LocationManager locationManager;;
    LocationListener locationListener;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemaps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //waits asyncro. to ge the map, this class handles map
    }

    private void setNewMarker(final Context c)//To be called in onMapReady
    {
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() { //Long click map listener
            @Override
            public void onMapLongClick(final LatLng latLng) {
                final MarkerOptions markerOptions = new MarkerOptions(); //Creates marker
                markerOptions.position(latLng); //Sets pos for marker
                final EditText taskEditText = new EditText(c); //Edittext with c, context
                AlertDialog dialog = new AlertDialog.Builder(c) //Dialog builder
                        .setTitle("Enter a name for your mark!")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { //What to do if success
                                Map<String, Object> location = new HashMap<>();
                                String title = String.valueOf(taskEditText.getText());
                                GeoPoint geoPoint = new GeoPoint(latLng.latitude,latLng.longitude);
                                location.put("title",title);
                                location.put("position",geoPoint);
                                markerOptions.title(title);
                                mMap.addMarker(markerOptions);
                                db.collection(coordinates).document().set(location);
                            }
                        })
                        .setNegativeButton("Cancel", null) //Negative button
                        .create(); //Creates dialog
                dialog.show(); // show dialog
            }
        });
    }

    private void readCoordinates() {
        db.collection(coordinates)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            String title = (String) documentSnapshot.get("title");
                            GeoPoint geoPoint = documentSnapshot.getGeoPoint("position");
                            LatLng latLng = new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(latLng).title(title));

                        }
                    }
                });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                updateMap(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        setNewMarker(MapsActivity.this);
        readCoordinates();
        }
        public void updateMap (Location location)
        {
            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

            mMap.clear();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
            mMap.addMarker(new MarkerOptions().position(userLocation).title("Your location"));


        }
}
