package ru.practicum.android.diploma.search.ui

import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ProgressBarRecyclerItemBinding

class ProgressBarViewHolder(private val binding: ProgressBarRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(isLastPage: Boolean) {
        Log.d("PROGRESS_HOLDER", "$isLastPage")
        binding.loadingProgressBar.isVisible = !isLastPage
    }
}
