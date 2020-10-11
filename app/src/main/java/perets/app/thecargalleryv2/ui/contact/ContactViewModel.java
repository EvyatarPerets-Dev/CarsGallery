package perets.app.thecargalleryv2.ui.contact;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactViewModel extends ViewModel {
    //Final Properties for intents:
    public static final String DIAL_TO = "tel: +972504004669";
    public static final String CALL_APP_NOT_FOUND = "Can׳t find the required Call App, Try downloading it..";
    public static final String MAIL_TO = "mailto: evyatarp17@gmail.com";
    public static final String EMAIL_APP_NOT_FOUND = "Email app not found.. Try downloading an Email App";
    public static final String TO_INSTAGRAM = "https://www.instagram.com/peretscarstyle/?igshid=nk5y5rto6hnq";
    public static final String INSTAGRAM_APP_NOT_FOUND = "Instagram not found, Try downloading it.";
    public static final String TO_GOOGLE_STORE_INSTAGRAM = "https://play.google.com/store/apps/details?id=com.instagram.android&hl=en";
    public static  final String WIRELESS_NOT_FOUND ="Wireless not found..";
    public static final String TO_GOOGLE_STORE_DIAL = "https://play.google.com/store/search?q=call&c=apps&hl=en" ;
    public static final String TO_GOOGLE_STORE_MAIL = "https://play.google.com/store/apps/details?id=com.google.android.gm&hl=en";


    private MutableLiveData<String> mTxtContact = new MutableLiveData<>();
    private MutableLiveData<String> mTxtContactInstagram = new MutableLiveData<>();
    private MutableLiveData<String> mTxtAllCarsMoto = new MutableLiveData<>();

    public ContactViewModel() {
        mTxtContact.setValue("Feel free to contact me:");
        mTxtContactInstagram.setValue("Check out my Instagram:");
        mTxtAllCarsMoto.setValue("Accepting all type of cars!");
    }

    public LiveData<String> getTextContact() {
        return mTxtContact;
    }
    public LiveData<String> getmTxtContactInstagram() {
        return mTxtContactInstagram;
    }
    public LiveData<String> getmTxtAllCarsMoto() {
        return mTxtAllCarsMoto;
    }
}