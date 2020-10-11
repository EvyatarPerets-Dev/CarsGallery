package perets.app.thecargalleryv2.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/**
 * {@link AboutViewModel}
 * Setting the value of the MutableLiveData (text) and Exposing it as LiveData to the Fragment.
 */
public class AboutViewModel extends ViewModel {

    private MutableLiveData<String> mText;



    public AboutViewModel() {
        mText = new MutableLiveData<>();

        mText.postValue("Hello, \nMy name is Evyatar Perets, I׳m From Israel and I׳m 23 years old.\nI have always had a big passion for cars and at the age of 17 I decided to take this passion another step forward and study photography." +
                "\nToday I׳m a professional automotive photographer Managing big Instagram account where I share my shoots (check my instagram at the contact page).\nI׳m also traveling the world to shoot the most unique cars, and I like it a lot!"
                );

    }

    public LiveData<String> getText() {
        return mText;
    }

}