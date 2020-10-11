package perets.app.thecargalleryv2.ui.contact;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import perets.app.thecargalleryv2.R;

/**
 * {@link ContactFragment}
 *
 * onCreateView: 1) Inflating the layout
 *               2) Setting on click listeners (Dial, Email, Instagram).
 *
 * hasConnection: 1) Internet Connection Checking
 */
public class ContactFragment extends Fragment {


    private ContactViewModel contactViewModel;

    @SuppressWarnings("Convert2MethodRef")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel =
                new ViewModelProvider(this).get(ContactViewModel.class);

        View root = inflater.inflate(R.layout.fragment_contact, container, false);

        //Set onClick listeners: With intents.


        //Dial on click listener!
        root.findViewById(R.id.btnCall).setOnClickListener(v -> {

            Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse(ContactViewModel.DIAL_TO));
            if (dial.resolveActivity(getContext().getPackageManager()) != null) {
                startActivity(dial);
            } else {
                Snackbar.make(root.findViewById(R.id.main_container),ContactViewModel.CALL_APP_NOT_FOUND, Snackbar.LENGTH_LONG)
                        .setAction("Go to Google Store",view->{
                            if (hasConnection()){
                                Intent googleStore = new Intent(Intent.ACTION_VIEW, Uri.parse(ContactViewModel.TO_GOOGLE_STORE_DIAL));
                                startActivity(googleStore);
                            }
                        }).show();
            }
        });

        //Email on click listener!
        root.findViewById(R.id.btnEmail).setOnClickListener(v -> {
            Intent mail = new Intent(Intent.ACTION_SENDTO, Uri.parse(ContactViewModel.MAIL_TO));
            if (hasConnection()) {
                if (mail.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(mail);
                } else {
                    Toast.makeText(getContext(), ContactViewModel.EMAIL_APP_NOT_FOUND, Toast.LENGTH_LONG).show();
                    Intent googleStore = new Intent(Intent.ACTION_VIEW, Uri.parse(ContactViewModel.TO_GOOGLE_STORE_MAIL));
                    startActivity(googleStore);
                }
            }
        });

        //Instagram on click listener!
        root.findViewById(R.id.btnInstagram).setOnClickListener(v -> {
            if (hasConnection()) {
                // Move to my ig page.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ContactViewModel.TO_INSTAGRAM));
                if (hasConnection()) {
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException exc) {
                        Toast.makeText(getContext(), ContactViewModel.INSTAGRAM_APP_NOT_FOUND, Toast.LENGTH_SHORT).show();
                        Intent intentGoogleStore = new Intent(Intent.ACTION_VIEW, Uri.parse(ContactViewModel.TO_GOOGLE_STORE_INSTAGRAM));
                        startActivity(intentGoogleStore);
                    }
                }
            }
        });


        //return the view..
        return root;
    }


    //Check Device Network Connection:
    private boolean hasConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = connectivityManager.getActiveNetwork();
        if (network != null) {
            return true;
        } else {
            Toast.makeText(getContext(), ContactViewModel.WIRELESS_NOT_FOUND, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                startActivity(intent);
            }
            return false;
        }
    }
}