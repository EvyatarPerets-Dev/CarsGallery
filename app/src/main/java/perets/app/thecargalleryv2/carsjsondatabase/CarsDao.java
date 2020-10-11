package perets.app.thecargalleryv2.carsjsondatabase;

import android.telecom.CallRedirectionService;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveCars(List<Cars> carsResults);

    @Query("SELECT * FROM Cars")
    List<Cars> getCars();

    @Query("SELECT * FROM Cars WHERE carName LIKE :carName")
    List<Cars> getCarsByName(String carName);
}
