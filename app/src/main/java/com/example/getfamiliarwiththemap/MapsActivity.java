package com.example.getfamiliarwiththemap;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.getfamiliarwiththemap.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button ok;

    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);



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
        mMap = googleMap;

        // Create pin
        final ImageView pin = new ImageView(MapsActivity.this);
        pin.setImageResource(R.drawable.common_google_signin_btn_icon_disabled);
        // Position pin
        FrameLayout.LayoutParams parameters = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        float density = getResources().getDisplayMetrics().density;

        parameters.bottomMargin = (int) (12 * density);
        pin.setLayoutParams( parameters);

        final MapView mapView = (MapView) findViewById(R.id.mapView) ;

        mapView.addView( pin);





        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin.setVisibility(View.INVISIBLE);
                LatLng selected = mMap.getProjection()
                        .fromScreenLocation( new Point(pin.getLeft() + (pin.getWidth()/2), pin.getBottom()));

                Marker address = mMap.addMarker(new MarkerOptions().title("Loading address...").position(selected));









            }
        });



        if (mMap != null){
            Marker hamburg = mMap.addMarker(new MarkerOptions().position(HAMBURG)
                    .title("Hamburg"));
            Marker kiel = mMap.addMarker(new MarkerOptions()
                    .position(KIEL)
                    .title("Kiel")
                    .snippet("Kiel is cool"));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Toast.makeText(MapsActivity.this,"You clicked a marker named " + marker.getTitle().toString(),Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            // Move the camera instantly to hamburg with a zoom of 15.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
            // Zoom in, animating the camera.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        }
    }
}
