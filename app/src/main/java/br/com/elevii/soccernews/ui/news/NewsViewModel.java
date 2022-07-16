package br.com.elevii.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.elevii.soccernews.data.remote.SoccerNewsApi;
import br.com.elevii.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews = new MutableLiveData<>();
    private final SoccerNewsApi service;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://talitaramos.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SoccerNewsApi.class);

        this.findNews();
    }

    private void findNews() {
        service.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if(response.isSuccessful()) {
                    mNews.setValue(response.body());
                } else {
                    System.out.println("error");
                    //TODO: pensar em uma estratégia de tratamento de erros
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                System.out.println("error");
               //TODO: pensar em uma estratégia de tratamento de erros
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}