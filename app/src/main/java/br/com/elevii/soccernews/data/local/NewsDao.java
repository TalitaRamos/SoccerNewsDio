package br.com.elevii.soccernews.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.elevii.soccernews.domain.News;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news WHERE favorite = :favorite")
    LiveData<List<News>> loadFavoriteNews(boolean favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news);
}
