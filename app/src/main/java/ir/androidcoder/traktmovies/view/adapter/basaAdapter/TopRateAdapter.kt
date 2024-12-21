package ir.androidcoder.traktmovies.view.adapter.basaAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.androidcoder.domain.entities.TopRateDEntity
import ir.androidcoder.traktmovies.databinding.NowPlayingItemBinding

class TopRateAdapter : BasePagingAdapter<TopRateDEntity, TopRateAdapter.TopRateViewHolder>(
    BaseDiffCallback(
        {oldItem, newItem -> oldItem.id == newItem.id},
        {oldItem, newItem -> oldItem == newItem}
    )
) {

    inner class TopRateViewHolder(private val binding: NowPlayingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: TopRateDEntity) {
            binding.apply {
                Log.v("testData12" , data.title)

                Glide.with(imgFilmCover.context)
                    .load("https://image.tmdb.org/t/p/w500" + data.poster_path)
                    .into(imgFilmCover)

                txtTitle.text = data.title
                txtDate.text = data.release_date

            }
        }
    }

    override fun onBindViewHolder(holder: TopRateViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRateViewHolder {
        val binding = NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context))
        return TopRateViewHolder(binding)
    }


}