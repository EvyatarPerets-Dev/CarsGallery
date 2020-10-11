package perets.app.thecargalleryv2.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.w3c.dom.ls.LSOutput;

/**
 * {@link Car} A class that describes 1 car with properties to build a List<Cars> at the {@link perets.app.thecargalleryv2.ui.gallery.GalleryViewModel}
 *             Includes each car name , photo from resources , and country flag.
 *
 *             also Implements Parcelable with his methods.
 */
public class Car implements Parcelable {

    private String carBrand;
    private int carPhoto;
    private String carCountry;


    public Car(String carBrand, int carPhoto, String carCountry) {
        this.carBrand = carBrand;
        this.carPhoto = carPhoto;
        this.carCountry = carCountry;
    }

    protected Car(Parcel in) {
        carBrand = in.readString();
        carPhoto = in.readInt();
        carCountry = in.readString();
    }

    @Override
    public String toString() {
        return "Car{" +
                "carBrand='" + carBrand + '\'' +
                ", carPhoto=" + carPhoto +
                ", carCountry='" + carCountry + '\'' +
                '}';
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getCarBrand() {
        return carBrand;
    }

    public int getCarPhoto() {
        return carPhoto;
    }

    public String getCarCountry() {
        return carCountry;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(carBrand);
        parcel.writeInt(carPhoto);
        parcel.writeString(carCountry);
    }
}
