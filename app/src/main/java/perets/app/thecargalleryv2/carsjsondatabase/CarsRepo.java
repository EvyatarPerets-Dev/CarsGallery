package perets.app.thecargalleryv2.carsjsondatabase;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.room.Room;

import org.threeten.bp.LocalDate;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ALL THE SOURCES ROOM DB + RETROFIT USAGE IN ONE CLASS -> REPOSITORY.
 */
public class CarsRepo {

    private static final String TAG = "CARS_REPO";
    //executor reuse instance:
    private Executor executor = Executors.newSingleThreadExecutor();

    //ui thread handler
    private Handler uiThread = new Handler(Looper.getMainLooper());

    //our database instance.
    public CarsDataBase db;

    private CarsRepo(Context context) {
        db = Room.databaseBuilder(context, CarsDataBase.class , "cars").build();
    }


    //singleton
    private static CarsRepo sharedInstance;

    public synchronized static CarsRepo getSharedInstance(Context context) {
        if (sharedInstance == null) {
            sharedInstance = new CarsRepo(context);
        }
        return sharedInstance;
    }


    //my interface callback
    public interface MyCarsCallback {
        void onResponse(List<String> cars, Exception exc);
    }

    //room using on background trade:
    private void saveCarsPhotos(List<Cars> cars) {

        db.getCarsDao().saveCars(cars);
    }

    private List<Cars> getAllCarsFromDB() {
        return db.getCarsDao().getCars();
    }

    private List<Cars> getCarsByNameFromDB(String carName) {
        return db.getCarsDao().getCarsByName(carName);
    }


    //the connecting method between Retrofit and Room database
    public void getCars(MyCarsCallback listener, LocalDate localDate, Activity activity, String carName) {

        // if == true -> update all photos from json to database
        if (getLastUpdateFile(localDate, activity)) {
            //cars from json methods:
            CarsDataSource.getSharedInstance().getCars(new Callback<CarsResult>() {
                @Override
                public void onResponse(Call<CarsResult> call, Response<CarsResult> response) {
                    //There is internet the information arrived
                    CarsResult body = response.body();
                    //run on ui thread

                        if (body == null) {
                            //if body null send null & null
                            listener.onResponse(null, null);
                            return;
                        }
                        //if we here we have the cars list - check the car name and bring the photos list
                        for (int i = 0; i < body.getCarResult().size(); i++) {
                            Cars cars = body.getCarResult().get(i);
                            if (cars.getCarName().toLowerCase().equals(carName.toLowerCase())) {
                                List<String> photos = cars.getPhotos();
                                listener.onResponse(photos, null);

                            }
                        }
                    uiThread.post(() -> {
                    });

                    //now background work save to database:
                    executor.execute(() -> {
                        if (body.getCarResult() == null)return;
                        saveCarsPhotos(body.getCarResult());

                    });

                    //save last update to the note
                    saveLastUpdateFile(localDate, activity);
                }

                @Override
                public void onFailure(Call<CarsResult> call, Throwable t) {
                    //here something went wrong we have no internet connection
                    //try to bring photos from database
                    executor.execute(() -> {
                        List<Cars> carsList = getCarsByNameFromDB(carName.toLowerCase());
                        for (int i = 0; i < carsList.size(); i++) {
                            Cars cars = carsList.get(i);
                            if (cars.getCarName().toLowerCase() == carName.toLowerCase()) {
                                //share listener on ui thread
                                uiThread.post(() -> {
                                    if (carsList.size() == 0) {
                                        listener.onResponse(null, new Exception(t));
                                    } else {
                                        //the list arrived from database:
                                        listener.onResponse(cars.getPhotos(), null);
                                    }
                                });
                            }
                        }

                    });
                }
            });
        } else {
            executor.execute(() -> {

                List<Cars> carsByNameFromDB = getCarsByNameFromDB(carName.toLowerCase());
                if (carsByNameFromDB == null) return;
                uiThread.post(() -> {
                for (int i = 0; i < carsByNameFromDB.size(); i++) {
                    Cars cars = carsByNameFromDB.get(i);
                    if (cars.getCarName().toLowerCase().equals(carName.toLowerCase())) {
                        //share listener on ui thread
                        cars.getPhotos();

                            listener.onResponse(cars.getPhotos(), null);

                    }
                }
                });
            });
        }


    }


    //Saving the last date update to the local file.
    public void saveLastUpdateFile(LocalDate localDate, Activity activity) {
        if (activity == null) return;
        SharedPreferences lastUpdateJson = activity.getSharedPreferences("jsonUpdate", Context.MODE_PRIVATE);

        lastUpdateJson.edit().putString("updateDate", String.valueOf(localDate)).apply();
    }

    //Getting the "LastUpdateJSON" file.
    public boolean getLastUpdateFile(LocalDate localDate, Activity activity) {
        if (activity == null) return false;

        SharedPreferences jsonUpdateFile = activity.getSharedPreferences("jsonUpdate", Context.MODE_PRIVATE);
        //
        String updateDate = jsonUpdateFile.getString("updateDate", null);

        if (updateDate == null) return true;

        LocalDate parseLocalDate = LocalDate.parse(updateDate);
        //if the localdate now is after the last update + month please let me know..
        if (localDate.isAfter(parseLocalDate.plusMonths(1))) {
            return true;
        } else {
            return false;
        }
    }
}
