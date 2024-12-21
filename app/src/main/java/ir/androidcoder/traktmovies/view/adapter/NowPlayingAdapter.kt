package ir.androidcoder.traktmovies.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.androidcoder.domain.entities.NowPlayingDEntity
import ir.androidcoder.traktmovies.databinding.NowPlayingItemBinding
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BaseDiffCallback
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BasePagingAdapter

class NowPlayingAdapter(val onCover :(String , String) -> Unit) : BasePagingAdapter<NowPlayingDEntity, NowPlayingAdapter.NowPlayingViewHolder>(
    BaseDiffCallback(
        {oldItem, newItem -> oldItem.id == newItem.id},
        {oldItem, newItem -> oldItem == newItem}
    )
) {

    inner class NowPlayingViewHolder(private val binding: NowPlayingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: NowPlayingDEntity) {
            binding.apply {
                Log.v("testData12" , data.title)

                Glide.with(imgFilmCover.context)
                        .load("https://image.tmdb.org/t/p/w500" + data.poster_path)
                        .into(imgFilmCover)

                    txtTitle.text = data.title
                    txtDate.text = data.release_date

                    onCover("https://image.tmdb.org/t/p/w780" + data.backdrop_path , data.title)

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