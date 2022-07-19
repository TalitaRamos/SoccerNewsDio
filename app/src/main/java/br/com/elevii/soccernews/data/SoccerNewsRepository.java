package br.com.elevii.soccernews.data;

import androidx.room.Room;

import br.com.elevii.soccernews.App;
import br.com.elevii.soccernews.data.local.SoccerNewsDb;
import br.com.elevii.soccernews.data.remote.SoccerNewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {
    private static final String REMOTE_API_URL = "https://talitaramos.github.io/soccer-news-api/";
    private static final String LOCAL_DB_NAME = "soccer-news.database";

    private SoccerNewsApi remoteAPi;
    private SoccerNewsDb localDb;

    public SoccerNewsApi getRemoteAPi() {
        return remoteAPi;
    }

    public SoccerNewsDb getLocalDb() {
        return localDb;
    }

    private SoccerNewsRepository () {
        remoteAPi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), SoccerNewsDb.class, LOCAL_DB_NAME)
                .build();
    }

    public static SoccerNewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static class LazyHolder {
        private static final  SoccerNewsRepository INSTANCE  = new SoccerNewsRepository();
    }
}
