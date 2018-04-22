package prosolupov.konstantin.ru.taskyandexschool;

import android.app.Application;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import prosolupov.konstantin.ru.taskyandexschool.retrofit.GetData;
import prosolupov.konstantin.ru.taskyandexschool.retrofit.UnsafeOkHttpClient;
import retrofit.converter.GsonConverter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static GetData getData;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient client = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://cloud-api.yandex.net:443/v1/disk/resources/")
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getData = retrofit.create(GetData.class);
    }

    public static GetData getData(){
        return getData;
    }
}
