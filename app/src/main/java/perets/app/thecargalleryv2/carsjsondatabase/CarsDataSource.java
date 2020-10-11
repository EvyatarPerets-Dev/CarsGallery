package perets.app.thecargalleryv2.carsjsondatabase;

import android.util.Log;

import com.google.gson.GsonBuilder;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarsDataSource {

    private static final String TAG = "CARS_DATA";
    //retrofit init
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/v0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //interface instance
    private CarsService service = retrofit.create(CarsService.class);


    //singleton
    private static final String token = "d514233b-0da2-4c40-8bce-d62aedd97ba6";
    private static final String mediaType = "media";


    public CarsDataSource() {}

    private static CarsDataSource sharedInstance;


    public synchronized static CarsDataSource getSharedInstance() {
        if (sharedInstance == null){
            sharedInstance = new CarsDataSource();
        }
        return sharedInstance;
    }

    public void getCars(Callback<CarsResult> callback){

        service.getCars(mediaType,token).enqueue(callback);
    }
}
