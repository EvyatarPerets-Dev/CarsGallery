package perets.app.thecargalleryv2.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import perets.app.thecargalleryv2.R;
import perets.app.thecargalleryv2.models.Car;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Car>> myImages;

    private MutableLiveData<Car> selectedCar = new MutableLiveData<>();


    public GalleryViewModel() {
        myImages = new MutableLiveData<>();
        ArrayList<Car> cars = new ArrayList<>();

        cars.add(new Car("Ferrari", R.drawable.ferrari , "Italy 🇮🇹"));
        cars.add(new Car("Lamborghini", R.drawable.lamborghini, "Italy 🇮🇹"));
        cars.add(new Car("Porsche", R.drawable.porsche , "Germany 🇩🇪"));
        cars.add(new Car("Mclaren", R.drawable.mclaren , "UK 🇬🇧"));
        cars.add(new Car("AstonMartin", R.drawable.astonmartin , "UK 🇬🇧"));
        cars.add(new Car("Mercedes", R.drawable.mercedes , "Germany 🇩🇪"));
        cars.add(new Car("Audi", R.drawable.audi , "Germany 🇩🇪"));
        cars.add(new Car("BMW", R.drawable.bmw , "Germany 🇩🇪"));
        cars.add(new Car("Bentley", R.drawable.bentley , "UK 🇬🇧"));
        cars.add(new Car("RollsRoyce", R.drawable.rollsroyce, "Germany 🇩🇪"));
        cars.add(new Car("AlfaRomeo", R.drawable.alfaromeo , "Italy 🇮🇹"));
        cars.add(new Car("Maserati", R.drawable.maserati, "Italy 🇮🇹"));
        cars.add(new Car("JDM", R.drawable.jdm , "Japan 🇯🇵"));
        cars.add(new Car("AmericanMuscles", R.drawable.americanmuscles , "USA 🇺🇸"));
;




        //setting the value inside.
        myImages.postValue(cars);



    }

    public LiveData<ArrayList<Car>> getMyImages() {
        return myImages;
    }

    public MutableLiveData<Car> getSelectedCar() {
        return selectedCar;
    }
}