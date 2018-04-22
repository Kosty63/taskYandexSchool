package prosolupov.konstantin.ru.taskyandexschool.retrofit;

import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Flowable;
import prosolupov.konstantin.ru.taskyandexschool.model.Items;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetData {
    @GET("https://cloud-api.yandex.net/v1/disk/resources/last-uploaded?limit=20&media_type=image")
    Flowable<JsonObject> getImage(@Header("Authorization") String token);
}
