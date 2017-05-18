package ru.kpfu.chirkov.learnprogramming.data.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.kpfu.chirkov.learnprogramming.BuildConfig;

/**
 * @author ilya
 */

public final class ApiFactory {

    private static OkHttpClient sClient;

    private static volatile HackerOneService sHackerOneService;
    private static volatile LearnProgrammingService sLearnProgrammingService;

    private ApiFactory() {
    }

    @NonNull
    public static HackerOneService getHackerOneService() {
        HackerOneService service = sHackerOneService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sHackerOneService;
                if (service == null) {
                    service = sHackerOneService = buildHackerOneRetrofit().create(HackerOneService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static LearnProgrammingService getLearnProgrammingService() {
        LearnProgrammingService service = sLearnProgrammingService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sLearnProgrammingService;
                if (service == null) {
                    service = sLearnProgrammingService = buildLearnProgrammingRetrofit().create(LearnProgrammingService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        sClient = null;
        sClient = getClient();
        sHackerOneService = buildHackerOneRetrofit().create(HackerOneService.class);
        sLearnProgrammingService = buildLearnProgrammingRetrofit().create(LearnProgrammingService.class);
    }

    @NonNull
    private static Retrofit.Builder buildBaseRetrofit() {
        return new Retrofit.Builder()
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    @NonNull
    private static Retrofit buildHackerOneRetrofit() {
        return buildBaseRetrofit()
                .baseUrl(BuildConfig.HACKERONE_API_ENDPOINT)
                .build();
    }

    @NonNull
    private static Retrofit buildLearnProgrammingRetrofit() {
        return buildBaseRetrofit()
                .baseUrl(BuildConfig.MAIN_API_ENDPOINT)
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}