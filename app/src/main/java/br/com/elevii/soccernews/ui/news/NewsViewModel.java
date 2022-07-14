package br.com.elevii.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.elevii.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews;

    public NewsViewModel() {
        this.mNews = new MutableLiveData<>();

        List<News> news = new ArrayList<>();

        news.add(new News("Ferroviária tem Desfalque Importante","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur scelerisque tincidunt orci, non venenatis mauris condimentum a. Integer tristique nisi id sagittis volutpat. In scelerisque enim ut massa pulvinar commodo."));
        news.add(new News("Ferrinha Joga no Sábado","Integer accumsan metus vel risus tincidunt pellentesque. Proin luctus ipsum nisi, ut tempor tortor interdum ac."));
        news.add(new News("Caopa do Mundo Feminina Está Terminado","Pellentesque nec ultrices elit. Vestibulum bibendum leo a lorem vehicula facilisis in ac tellus. Etiam faucibus feugiat dolor at gravida. Suspendisse potenti. Aliquam mattis viverra risus, nec sodales arcu vestibulum in. Vestibulum quis risus eget nisi aliquam commodo. Praesent mollis vestibulum condimentum."));

        this.mNews.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}