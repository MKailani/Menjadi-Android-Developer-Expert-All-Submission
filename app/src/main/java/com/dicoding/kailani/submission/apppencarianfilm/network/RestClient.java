package com.dicoding.kailani.submission.apppencarianfilm.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dicoding.kailani.submission.apppencarianfilm.BuildConfig.MOVIE_URL;

/**
 * Dicoding Academy
 * Submission 1 - Aplikasi Pencarian Film
 *
 * Created by Kailani on 04/01/19.
 */
public class RestClient {

    private static final String BASE_URL = MOVIE_URL;
    private ApiService apiService;
    private static final int TIMEOUT_DEFAULT = 20;
    private static final int TIMEOUT_LONG = 120;

    public RestClient() {
        this(false);
    }

    private RestClient(boolean longRequest) {
        this(longRequest, BASE_URL);
    }

    private RestClient(boolean longRequest, String mBaseUrl) {
        int TIMEOUT = TIMEOUT_DEFAULT;

        if (longRequest) {
            TIMEOUT = TIMEOUT_LONG;
        }

        // Logging Service Success or not REST Client
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Config OKHTTP
        OkHttpClient.Builder okClientConfig = new OkHttpClient.Builder();
        okClientConfig.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        okClientConfig.connectTimeout(TIMEOUT, TimeUnit.SECONDS);

        okClientConfig.addInterceptor(interceptor);

        // Set Inisialization Config OKHTTP for OKHttpClient
        OkHttpClient okClient = okClientConfig.build();

        // Retrofit Client
        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofitClient.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
