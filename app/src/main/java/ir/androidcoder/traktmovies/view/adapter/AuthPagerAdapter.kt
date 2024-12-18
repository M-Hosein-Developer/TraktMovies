package ir.androidcoder.traktmovies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.androidcoder.traktmovies.databinding.ImagePagerLayoutBinding

class AuthPagerAdapter(private val image : List<Int> , val onPosition :(Int) -> Unit) : RecyclerView.Adapter<AuthPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(private val binding : ImagePagerLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(image: Int) {
            binding.apply {
                Glide.with(imgCover.context)
                    .load(image)
                    .into(imgCover)


            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ImagePagerLayoutBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int = image.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int){
        holder.bind(image[position])
        onPosition(holder.absoluteAdapterPosition)
    }

}