package com.anar.PEAK;


import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class GpsUtils extends Service implements LocationListener {

    private final Context context;
    private Boolean isGpsEnabled = false, isNetworkEnabled = false, canGetLocation = false;
    public static final int Time_Bw_Updates = 1000 * 60 * 1;//one minute
    public static final int Min_Distance_For_Update = 10;
    private LocationManager locationManager;
    private Location location;
    private Double longitude;
    private Double latitude;


    public GpsUtils(Context context) {
        this.context = context;
        getlocation();
    }

    public Location getlocation() throws SecurityException{
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isNetworkEnabled && !isGpsEnabled) {
            Toast.makeText(context, "!isNetworkEnabled && !isGpsEnabled", Toast.LENGTH_SHORT).show();
            //no provider
            canGetLocation = false;
            return null;
        } else {
            canGetLocation = true;
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
//                return null;
//            }
            //get location using network
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
                        , Time_Bw_Updates
                        , Min_Distance_For_Update
                        , this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        return location;
                    }
                }
            }
            //get location using GPS
            if (isGpsEnabled && location == null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Time_Bw_Updates, Min_Distance_For_Update, this);
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        return location;
                    }
                }
            }
            return null;
        }
    }


    public Location getLocation() {
        return location;
    }

    public Double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public Double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public Boolean getCanGetLocation() {
        return this.canGetLocation;
    }

    public void showGpsAlertDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("GPS")
                .setMessage("GPS فعال نیست. آیا مایلید تنظیمات را انجام دهید؟")
                .setPositiveButton("Setting", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertBuilder.show();
    }
    public void stopUsingGps(){
        if (locationManager!=null){
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
