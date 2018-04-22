package prosolupov.konstantin.ru.taskyandexschool.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.database.Observable;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.JsonObject;
import com.yandex.disk.rest.RestClient;

import com.yandex.disk.rest.json.Resource;
import com.yandex.disk.rest.util.ResourcePath;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import prosolupov.konstantin.ru.taskyandexschool.App;
import prosolupov.konstantin.ru.taskyandexschool.R;
import prosolupov.konstantin.ru.taskyandexschool.model.Items;
import prosolupov.konstantin.ru.taskyandexschool.retrofit.GetData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);
        ButterKnife.bind(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://oauth.yandex.ru/authorize?response_type=token&client_id=b46228f475e5434f8fe14d8f42fb98ac");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("https://yxb46228f475e5434f8fe14d8f42fb98ac")){
                    Log.i("URL", url);
                    Pattern pattern = Pattern.compile("access_token=(.*?)(&|$)");
                    Matcher matcher = pattern.matcher(url);
                    if (matcher.find()){
                        final String token = matcher.group(1);
                        Log.i("Pattern", token);

/*                        App.getData().getImage(token).enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                Log.i("Response", response.body().toString());
                            }

                            @Override
                            public void onFailure(Call<JsonObject> call, Throwable t) {
                                Log.e("Error", t.toString());
                            }
                        });*/

                        App.getData().getImage(token)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new FlowableSubscriber<JsonObject>() {
                                    @Override
                                    public void onSubscribe(Subscription s) {

                                    }

                                    @Override
                                    public void onNext(JsonObject jsonObject) {
                                        Log.i("JsonObject", jsonObject.getAsJsonArray().get(1).getAsString());

                                    }

                                    @Override
                                    public void onError(Throwable t) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }

                }
                return true;
            }
        });

    }


}
