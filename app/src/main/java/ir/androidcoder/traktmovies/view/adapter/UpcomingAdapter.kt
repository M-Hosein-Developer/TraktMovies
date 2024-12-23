package ir.androidcoder.traktmovies.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.androidcoder.domain.entities.MoviesEntity
import ir.androidcoder.traktmovies.databinding.NowPlayingItemBinding
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BaseDiffCallback
import ir.androidcoder.traktmovies.view.adapter.basaAdapter.BasePagingAdapter

class UpcomingAdapter (val onClick :(Int) -> Unit) : BasePagingAdapter<MoviesEntity, UpcomingAdapter.UpcomingViewHolder>(
    BaseDiffCallback(
        {oldItem, newItem -> oldItem.id == newItem.id},
        {oldItem, newItem -> oldItem == newItem}
    )
) {

    inner class UpcomingViewHolder(private val binding: NowPlayingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: MoviesEntity) {
            binding.apply {
                Log.v("testData12" , data.title)

                Glide.with(imgFilmCover.context)
                    .load("https://image.tmdb.org/t/p/w500" + data.posterPath)
                    .into(imgFilmCover)

                txtTitle.text = data.title
                txtDate.text = data.releaseDate

                itemView.setOnClickListener {
                    onClick(data.id)
                }

            }
        }
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val binding = NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context))
        return UpcomingViewHolder(binding)
    }


}