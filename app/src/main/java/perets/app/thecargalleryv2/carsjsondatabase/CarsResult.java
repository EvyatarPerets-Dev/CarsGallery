package perets.app.thecargalleryv2.carsjsondatabase;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarsResult {

    @SerializedName("carResult")
    public List<Cars> carResult;
    public List<Cars> getCarResult() {
        return carResult;
    }

    public CarsResult() {
    }

    @Override
    public String toString() {
        return "CarsResult{" +
                "carResult=" + carResult +
                '}';
    }


}
