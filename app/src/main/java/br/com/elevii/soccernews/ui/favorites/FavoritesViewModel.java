package br.com.elevii.soccernews.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.elevii.soccernews.data.SoccerNewsRepository;
import br.com.elevii.soccernews.domain.News;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {}

    public LiveData<List<News>> loadFavoritesNews() {
        return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews(true);
    }

    public void saveNews(News news) {
        SoccerNewsRepository.getInstance().getLocalDb().newsDao().insert(news);
    }
}