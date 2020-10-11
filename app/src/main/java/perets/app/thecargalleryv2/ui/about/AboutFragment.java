package perets.app.thecargalleryv2.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import perets.app.thecargalleryv2.R;


/**
 * {@link AboutFragment} have an instance of {@link AboutViewModel}
 * Posting the text on the Fragment.
 */
public class AboutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutViewModel aboutViewModel = new ViewModelProvider(this).get(AboutViewModel.class);

       View root = inflater.inflate(R.layout.fragment_about ,container, false );

        final TextView textView = root.findViewById(R.id.text_about);

        aboutViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));


        return root;
    }
}