package perets.app.thecargalleryv2;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.snackbar.Snackbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import perets.app.thecargalleryv2.models.MyInternetReceiver;

/**
 * Main activity -> Includes Bottom Navigation with 3 Fragments.
 * ----------------------------------------------------------------------------
 * onCreate: 1) Setting my layout & navigation.
 * 2) Registering my broadcast Receiver to get Internet status.
 * <p>
 * ----------------------------------------------------------------------------
 * <p>
 * onOptionsItemSelected: 1) Getting the selected item and take it back to the previous Fragment / Activity.
 * <p>
 * ----------------------------------------------------------------------------
 * <p>
 * onResume: 1) Init my singleTon (MyInternetReceiver.connectivityReceiverListener).
 * <p>
 * ----------------------------------------------------------------------------
 * <p>
 * onNetworkConnectionChanged: 1) Calls showNetworkMessage With a Boolean parameter -> "onNetworkConnectionChanged" comes from {@link MyInternetReceiver.ConnectivityReceiverListener} interface
 * <p>
 * ----------------------------------------------------------------------------
 * <p>
 * showNetworkMessage: 1) Checking if the user have internet: If no connection Sending SnackBar with a button "go to settings".
 * If has connection dismiss the snackBar.
 */
public class MainActivity extends AppCompatActivity implements MyInternetReceiver.ConnectivityReceiverListener {

    NavController navController;

    private InterstitialAd mInterstitialAd;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_gallery, R.id.navigation_about, R.id.navigation_contact)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //registering my broadcast receiver with intent filter.
        registerReceiver(new MyInternetReceiver(),
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        //Init Ads ->
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        mInterstitialAd = new InterstitialAd(this); //test code ->
        mInterstitialAd.setAdUnitId("ca-app-pub-2242268142817928/2884289889");
        // "ca-app-pub-3940256099942544/1033173712" -> my test app ad codes

        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        adsHandel();
    }

    private void adsHandel() {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("TAG", "onAdLoaded: ");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.d("TAG", "onAdLoaded: " + adError);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            if (++counter % 3 == 0) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
            navController.popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //init the interface.
        MyInternetReceiver.connectivityReceiverListener = this;

    }


    @Override
    public void onNetworkConnectionChanged(Boolean isConnected) {
        showNetworkMessage(isConnected);
    }

    private void showNetworkMessage(Boolean isConnected) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main_container), "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Go to settings", v -> {
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            startActivity(intent);
        });
        if (!isConnected) {
            snackbar.show();
        } else {
            snackbar.dismiss();
        }
    }



}