package perets.app.thecargalleryv2.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * MyInternetReceiver -> Extend BroadcastReceiver
 *
 * onReceive: Checking if the connectivityReceiver interface is not null
 *            then calling his method "onNetworkConnectionChanged"
 *            with boolean that says if the user is connected or connecting
 *
 * isConnectedOrConnecting: getting the activeNetwork and returning the status of the connection True or False
 *
 * {@link ConnectivityReceiverListener} Interface That have a method to listen for the changes of the connections (true or false).
 *
 * and then singleton for the interface
 */
public class MyInternetReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (connectivityReceiverListener != null ){
            //Internet Changed.
            connectivityReceiverListener.onNetworkConnectionChanged(isConnectedOrConnecting(context));
        }
    }


    private boolean isConnectedOrConnecting(Context context){
        //getting the connectivity manager
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //getting the network info
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        //true or false return type if true there is connection
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    //Interface That have a method to listen for the changes of the connections (true or false).
    public interface ConnectivityReceiverListener{
        void onNetworkConnectionChanged(Boolean isConnected);
    }
    
    //Singleton for the Interface
    public static ConnectivityReceiverListener connectivityReceiverListener = null;
    
}
