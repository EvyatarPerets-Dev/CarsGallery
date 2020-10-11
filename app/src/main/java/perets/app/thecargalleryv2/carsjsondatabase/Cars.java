package perets.app.thecargalleryv2.carsjsondatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

@Entity
public class Cars {
    @SerializedName("carName")
    @PrimaryKey
    @NonNull
    public String carName = "";

    @SerializedName("photoLinks")
    public List<String> photos;

    public String getCarName() {
        return carName;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public Cars() {
    }

    @Override
    public String toString() {
        return "Cars{" +
                "title='" + carName + '\'' +
                ", photos=" + photos +
                '}';
    }
}