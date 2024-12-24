package ir.androidcoder.traktmovies.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.androidcoder.domain.entities.SearchEntity
import ir.androidcoder.traktmovies.databinding.NowPlayingItemBinding
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BaseDiffCallback
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BasePagingAdapter

class SearchAdapter : BasePagingAdapter<SearchEntity, SearchAdapter.SearchViewHolder>(
    BaseDiffCallback(
        {oldItem, newItem -> oldItem.id == newItem.id},
        {oldItem, newItem -> oldItem == newItem}
    )
) {

    inner class SearchViewHolder(private val binding: NowPlayingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: SearchEntity) {
            binding.apply {

                Log.v("testData124" , data.name.toString())
//
                Glide.with(imgFilmCover.context)
                        .load("https://image.tmdb.org/t/p/w500" + data.posterPath)
                        .into(imgFilmCover)

                    txtTitle.text = data.name
                    txtDate.text = data.originalName
//
//

            }
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context))
        return SearchViewHolder(binding)
    }


}