package br.com.elevii.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import br.com.elevii.soccernews.MainActivity;
import br.com.elevii.soccernews.databinding.FragmentFavoriteBinding;
import br.com.elevii.soccernews.domain.News;
import br.com.elevii.soccernews.ui.adapter.NewsAdapter;

public class FavoritesFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loadFavoritesNews();

        return root;
    }

    private void loadFavoritesNews() {
        MainActivity activity = (MainActivity) getActivity();
        List<News> favoriteNews = activity.getDb().newsDao().loadFavoriteNews(true);

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNews.setAdapter(new NewsAdapter(favoriteNews, updatedNews -> {
            activity.getDb().newsDao().insert(updatedNews);
            loadFavoritesNews();
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}