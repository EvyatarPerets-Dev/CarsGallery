package perets.app.thecargalleryv2.carsjsondatabase;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarsService {

    @GET("b/the-car-gallery.appspot.com/o/Cars%2FJsonFile%2Fcars.json")
    Call<CarsResult> getCars(@Query("alt") String media ,@Query("token") String token);
}






//Base:
//https://firebasestorage.googleapis.com/v0/

//Path:
// b/the-car-gallery.appspot.com/o/Cars%2FJsonFile%2Fcars.json

//query strings
// ?alt=  media  &  token=  adb7632a-6fd5-4228-b786-61fe98e4e39f


//  https://firebasestorage.googleapis.com/v0/b/the-car-gallery.appspot.com/o/Cars%2FJsonFile%2Fcars.json?alt=media&token=d514233b-0da2-4c40-8bce-d62aedd97ba6