package ir.androidcoder.traktmovies.view.adapter.basaAdapter

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

abstract class BasePagingAdapter<T : Any , VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>,
) : PagingDataAdapter<T, VH>(diffCallback){


    fun removeItem(position: Int, lifecycleScope: LifecycleCoroutineScope) {
        if (position != RecyclerView.NO_POSITION) {
            val currentItems = snapshot().items.toMutableList()
            if (position in currentItems.indices) {
                currentItems.removeAt(position)
                val newPagingData = PagingData.from(currentItems)
                lifecycleScope.launch {
                    submitData(newPagingData)
                }
            }
        }
    }

    fun addItem(position: Int , newData : T , lifecycleScope: LifecycleCoroutineScope){
        val currentItems = snapshot().items.toMutableList()
        currentItems.add(position , newData)
        val newPagingData = PagingData.from(currentItems)
        lifecycleScope.launch {
            submitData(newPagingData)
        }
    }

    fun removeAllItem(lifecycleScope: LifecycleCoroutineScope){
        val currentItems = snapshot().items.toMutableList()
        currentItems.clear()
        val newPagingData = PagingData.from(currentItems)
        lifecycleScope.launch {
            submitData(newPagingData)
        }
    }

}