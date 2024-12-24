package ir.androidcoder.traktmovies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.androidcoder.domain.entities.SearchEntity
import ir.androidcoder.traktmovies.databinding.NowPlayingItemBinding
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BaseDiffCallback
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BasePagingAdapter

class SearchAdapter : BasePagingAdapter<SearchEntity.Result, SearchAdapter.NowPlayingViewHolder>(
    BaseDiffCallback(
        {oldItem, newItem -> oldItem.id == newItem.id},
        {oldItem, newItem -> oldItem == newItem}
    )
) {

    inner class NowPlayingViewHolder(private val binding: NowPlayingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: SearchEntity.Result) {
            binding.apply {

//                Log.v("testData12" , data.title)
//
//                Glide.with(imgFilmCover.context)
//                        .load("https://image.tmdb.org/t/p/w500" + data.posterPath)
//                        .into(imgFilmCover)
//
//                    txtTitle.text = data.title
//                    txtDate.text = data.releaseDate
//
//                    onCover("https://image.tmdb.org/t/p/w780" + data.backdropPath , data.title)
//
//                itemView.setOnClickListener {
//                    onClick(data.id)
//                }

            }
        }
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val binding = NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context))
        return NowPlayingViewHolder(binding)
    }


}