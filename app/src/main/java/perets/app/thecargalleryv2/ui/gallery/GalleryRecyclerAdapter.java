package perets.app.thecargalleryv2.ui.gallery;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import perets.app.thecargalleryv2.R;
import perets.app.thecargalleryv2.models.Car;

public class GalleryRecyclerAdapter extends RecyclerView.Adapter<GalleryRecyclerAdapter.MyViewHolder> {

    private final static int FADE_DURATION = 1000;
    private ArrayList<Car> cars;

    private MutableLiveData<Car> selectedCar;

    public GalleryRecyclerAdapter(ArrayList<Car> cars, MutableLiveData<Car> selectedCar) {
        this.cars = cars;
        this.selectedCar = selectedCar;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        setScaleAnimation(holder.itemView);

        Car car = cars.get(position);

        holder.carBrandTV.setText(car.getCarBrand());
        holder.carCountryTV.setText(car.getCarCountry());
        holder.carBrandPhoto.setImageResource(car.getCarPhoto());

        //onClick pass car!
        holder.itemView.setOnClickListener(v -> {
            selectedCar.postValue(car);
        });

    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation
                (0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    @Override
    public int getItemCount() {
        return cars.size();
    }


    //Represent 1 item in the recycler.
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView carBrandTV;
        TextView carCountryTV;
        ImageView carBrandPhoto;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            carBrandTV = itemView.findViewById(R.id.tv_car_brand);
            carCountryTV = itemView.findViewById(R.id.tv_car_country);
            carBrandPhoto = itemView.findViewById(R.id.carBrandIV);


        }
    }
}
