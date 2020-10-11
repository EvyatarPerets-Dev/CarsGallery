package perets.app.thecargalleryv2.ui.SharePhotosGallery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.threeten.bp.LocalDate;

import java.util.List;
import java.util.concurrent.Executors;

import perets.app.thecargalleryv2.R;
import perets.app.thecargalleryv2.carsjsondatabase.Cars;
import perets.app.thecargalleryv2.carsjsondatabase.CarsRepo;
import perets.app.thecargalleryv2.models.Car;


public class SharePhotosFragment extends Fragment {

    private SharePhotosViewModel mViewModel;

    ImageView galleryPhotos;
    RecyclerView recyclerShowPhotos;
    TextView changeLayoutBtn;
    boolean layout;

    public static SharePhotosFragment newInstance() {
        return new SharePhotosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SharePhotosViewModel.class);
        View root = inflater.inflate(R.layout.share_photos_fragment, container, false);

        recyclerShowPhotos = root.findViewById(R.id.recyclerShowPhotos);
        galleryPhotos = root.findViewById(R.id.galleryPhotos);
        changeLayoutBtn = root.findViewById(R.id.changeLayoutBtn);
        layout = false;

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //get args from gallery fragments
        Bundle arguments = getArguments();
        if (getArguments() == null) return;
        //get bundle item called car
        Car car = arguments.getParcelable("car");
        if (car == null) return;

        if (getContext() == null) return;

        CarsRepo sharedInstance = CarsRepo.getSharedInstance(getContext());

        sharedInstance.getCars(new CarsRepo.MyCarsCallback() {
            @Override
            public void onResponse(List<String> cars, Exception exc) {

                if (exc != null) return;

                PhotosRecyclerAdapter recycler = new PhotosRecyclerAdapter(cars);
                recyclerShowPhotos.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerShowPhotos.setAdapter(recycler);


                changeLayoutBtn.setOnClickListener(v->{
                    if (!layout) {
                        recyclerShowPhotos.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false));
                        recyclerShowPhotos.setAdapter(recycler);
                        layout = true;
                    }else {
                        recyclerShowPhotos.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerShowPhotos.setAdapter(recycler);
                        layout = false;
                    }
                });


            }
        }, LocalDate.now(), getActivity(), car.getCarBrand().toLowerCase());

    }
}