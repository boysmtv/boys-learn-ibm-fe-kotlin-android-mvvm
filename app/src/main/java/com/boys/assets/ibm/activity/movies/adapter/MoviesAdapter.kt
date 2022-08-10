package com.boys.assets.ibm.activity.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.ibm.BuildConfig
import com.boys.assets.ibm.activity.movies.model.MoviesData
import com.boys.assets.ibm.activity.movies.model.MoviesModel
import com.boys.assets.ibm.activity.movies.model.MoviesReqModel
import com.boys.assets.ibm.activity.movies.presentation.MoviesActivity
import com.boys.assets.ibm.activity.movies.presentation.MoviesOnClickListener
import com.boys.assets.ibm.databinding.ActivityMoviesListItemBinding
import com.boys.assets.ibm.helper.InterfaceDialog
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.AddressHolder>() {

    private var listModel = mutableListOf<MoviesData>()
    private lateinit var listener : MoviesOnClickListener<MoviesData>

    fun provided(
        context: MoviesActivity,
        model: MoviesModel,
        moviesReqModel: MoviesReqModel,
        interfaceDialog: InterfaceDialog
    ) {
        interfaceDialog.dismisDialogLoading()
        this.listener = context
        this.listModel = model.results!!.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ActivityMoviesListItemBinding.inflate(inflater, parent, false)
        return AddressHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.listModel.size
    }

    override fun onBindViewHolder(holder: AddressHolder, position: Int) {
        val model = this.listModel[position]
        holder.bind(position, model, listener)
    }

    inner class AddressHolder(binding: ActivityMoviesListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val thisBinding: ActivityMoviesListItemBinding

        fun bind(position: Int, model: MoviesData, listener: MoviesOnClickListener<MoviesData>) {

            thisBinding.tvMvTitle.text = model.original_title

            Glide.with(thisBinding.root).load(BuildConfig.IMAGE_URL_LOW + model.poster_path).into(thisBinding.ivMvImage)

            // set on click listener
            thisBinding.layoutContent.setOnClickListener { view ->
                listener.onItemClick(
                    view,
                    position,
                    model
                )
            }
        }

        init {
            thisBinding = binding
        }
    }
}