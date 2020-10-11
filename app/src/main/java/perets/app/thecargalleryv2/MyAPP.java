package perets.app.thecargalleryv2;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * THE FATHER OF THE APP!
 * Init JackWalton LocalDate Lib.
 */
public class MyAPP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidThreeTen.init(this);
    }
}
