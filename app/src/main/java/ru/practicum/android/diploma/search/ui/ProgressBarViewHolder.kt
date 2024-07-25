package ru.practicum.android.diploma.search.ui

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.android.diploma.databinding.ProgressBarRecyclerItemBinding

class ProgressBarViewHolder(private val binding: ProgressBarRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(isLastPage: Boolean) {
        binding.loadingProgressBar.isVisible = !isLastPage
    }
}
