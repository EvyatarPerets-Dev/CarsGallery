package perets.app.thecargalleryv2.ui.SharePhotosGallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

public class SharePhotosViewModel extends ViewModel {

    MutableLiveData<HashMap<String , String[]> >images = new MutableLiveData<>();


    public SharePhotosViewModel(){


    }


    public LiveData<HashMap<String, String[]>> getImages() {
        return images;
    }
}