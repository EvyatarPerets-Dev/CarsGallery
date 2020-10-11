package perets.app.thecargalleryv2.carsjsondatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(version = 1, entities = {Cars.class}, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class CarsDataBase extends RoomDatabase {
   public abstract CarsDao getCarsDao();
}
