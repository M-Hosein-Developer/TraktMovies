package ir.androidcoder.traktmovies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.androidcoder.domain.entities.WatchListDEntity
import ir.androidcoder.traktmovies.databinding.NowPlayingItemBinding
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BaseDiffCallback
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BasePagingAdapter

class WatchListAdapter : BasePagingAdapter<WatchListDEntity, WatchListAdapter.WatchListViewHolder>(
    BaseDiffCallback(
        {oldItem, newItem -> oldItem.id == newItem.id},
        {oldItem, newItem -> oldItem == newItem}
    )
) {

    inner class WatchListViewHolder(private val binding: NowPlayingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: WatchListDEntity) {
            binding.apply {

                Glide.with(imgFilmCover.context)
                        .load("https://image.tmdb.org/t/p/w500" + data.posterPath)
                        .into(imgFilmCover)

                    txtTitle.text = data.title
                    txtDate.text = data.releaseDate

            }
        }
    }

    override fun onBindViewHolder(holder: WatchListViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListViewHolder {
        val binding = NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context))
        return WatchListViewHolder(binding)
    }


}