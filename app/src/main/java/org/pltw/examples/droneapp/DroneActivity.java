package org.pltw.examples.droneapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parrot.arsdk.ARSDK;
import com.parrot.arsdk.ardiscovery.ARDiscoveryDeviceService;


public class DroneActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "BebopActivity";
    private Drone mDrone;

    private ProgressDialog mConnectionProgressDialog;
    private ProgressDialog mDownloadProgressDialog;

    //private BebopVideoView mVideoView;

    private TextView mBatteryLabel;
    //private Button mTakeOffLandBt;
    //private Button mDownloadBt;

    private int mNbMaxDownload;
    private int mCurrentDownloadIndex;
    private GoogleMap mMap;
    private Double Lat;
    private Double Lng;
    private Dialog startDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drone);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initIHM();

        Intent intent = getIntent();
        ARDiscoveryDeviceService service = intent.getParcelableExtra(DeviceListActivity.EXTRA_DEVICE_SERVICE);
        mDrone = new Drone(this, service);
        mDrone.addListener(mDroneListener);
        ARSDK.loadSDKLibs();
        Location currentLocation = new Location("Project Fi");
        Lat = currentLocation.getLatitude();
        Lng = currentLocation.getLongitude();

        startDialog.show();
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

        // Add a marker at Current Location and move the camera
        LatLng currentLocationMarker = new LatLng(Lat, Lng);
        mMap.addMarker(new MarkerOptions().position(currentLocationMarker).title("Marker at Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocationMarker));
    }

}
