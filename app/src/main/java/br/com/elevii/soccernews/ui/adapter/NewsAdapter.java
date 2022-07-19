package br.com.elevii.soccernews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.elevii.soccernews.R;
import br.com.elevii.soccernews.databinding.NewsItemBinding;
import br.com.elevii.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> news;
    private final NewsAdapter.favoriteListener favoriteListener;

    public NewsAdapter(List<News> news, NewsAdapter.favoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = this.news.get(position);

        Context context = holder.itemView.getContext();

        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());
        Picasso.get()
                .load(news.getImage())
                .fit()
                .into(holder.binding.ivThumbnail);

        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(news.getLink()));
            holder.itemView.getContext().startActivity(intent);
        });

        holder.binding.ivShare.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, news.getTitle());
            intent.putExtra(Intent.EXTRA_TEXT, news.getLink());
            context.startActivity(Intent.createChooser(intent, "Share Via"));
        });

        //O evento será instanciado pelo fragment
        holder.binding.ivFavorite.setOnClickListener(view -> {
            news.setFavorite(!news.isFavorite());
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
        });

        int favoriteColor = news.isFavorite() ? context.getColor(R.color.red) : context.getColor(R.color.grey);

        holder.binding.ivFavorite.setColorFilter(favoriteColor);
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface favoriteListener {
        void onFavorite(News news);
    }
}
