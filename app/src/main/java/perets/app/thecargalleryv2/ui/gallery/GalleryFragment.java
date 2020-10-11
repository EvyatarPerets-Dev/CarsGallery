package perets.app.thecargalleryv2.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import perets.app.thecargalleryv2.R;
import perets.app.thecargalleryv2.models.Car;

public class GalleryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        RecyclerView rv = root.findViewById(R.id.recyclerGallery);

        //get the selected car:
        MutableLiveData<Car> selectedCar = galleryViewModel.getSelectedCar();

        //Load the recycler view + put image , titles ,and country photos:
        galleryViewModel.getMyImages().observe(getViewLifecycleOwner(), list->{
            GalleryRecyclerAdapter galleryRecyclerAdapter = new GalleryRecyclerAdapter(list, selectedCar);
            rv.setAdapter(galleryRecyclerAdapter);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
        });

        //observer the selected car:
        selectedCar.observe(getViewLifecycleOwner(), car->{
            if (car == null){return;}

            //find the main nav host:
            NavController navController = NavHostFragment.findNavController(this);

            //build parameters:
            Bundle args = new Bundle();
            //and pass them:
            args.putParcelable("car", car);

            //send him to this page by action arrow
            navController.navigate(R.id.action_to_sharePhotosFragment, args);


            //set null so it can be back to the previous page
            selectedCar.setValue(null);

        });

        return root;
    }
}