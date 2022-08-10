package com.boys.assets.ibm.activity.movies.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.boys.assets.ibm.activity.movies.adapter.MoviesAdapter
import com.boys.assets.ibm.activity.movies.model.MoviesData
import com.boys.assets.ibm.activity.movies.model.MoviesModel
import com.boys.assets.ibm.activity.movies.model.MoviesReqModel
import com.boys.assets.ibm.activity.movies.vm.MoviesViewModel
import com.boys.assets.ibm.activity.users.presentation.SelectedActivity
import com.boys.assets.ibm.databinding.ActivityMoviesBinding
import com.boys.assets.ibm.helper.InterfaceDialog
import com.boys.assets.ibm.utils.LogUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity(), MoviesOnClickListener<MoviesData> {

    private val TAG = this::class.java.simpleName
    private val thisContext = this@MoviesActivity

    private lateinit var binding            : ActivityMoviesBinding
    private lateinit var interfaceDialog    : InterfaceDialog

    private lateinit var popularAdapter     : MoviesAdapter
    private lateinit var topRatedAdapter    : MoviesAdapter
    private lateinit var upComingAdapter    : MoviesAdapter
    private lateinit var moviesModel        : MoviesModel
    private lateinit var moviesReqModel     : MoviesReqModel

    val POPULAR = "popular"
    val UPCOMING = "upcoming"
    val TOP_RATED = "top_rated"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding             = ActivityMoviesBinding.inflate(layoutInflater)

        interfaceDialog     = InterfaceDialog(thisContext)
        moviesModel         = MoviesModel()
        moviesReqModel      = MoviesReqModel()

        popularAdapter      = MoviesAdapter()
        topRatedAdapter     = MoviesAdapter()
        upComingAdapter     = MoviesAdapter()

        setContentView(binding.root)
        supportActionBar!!.hide()

        val VM by viewModel<MoviesViewModel>()

        setAddressVM(VM, popularAdapter, topRatedAdapter, upComingAdapter)
        setRequest(VM, binding, popularAdapter, topRatedAdapter, upComingAdapter)
    }

    /**
     * set view model
     */
    private fun setAddressVM(
        VM: MoviesViewModel,
        moviesAdapter: MoviesAdapter,
        topRatedAdapter: MoviesAdapter,
        upComingAdapter: MoviesAdapter
    ) {
        with(VM){
            onSuccess.observe(thisContext) {
                if (it.second.category == POPULAR){
                    moviesAdapter.provided(thisContext, it.first, moviesReqModel, interfaceDialog)
                }
                if (it.second.category == TOP_RATED){
                    topRatedAdapter.provided(thisContext, it.first, moviesReqModel, interfaceDialog)
                }
                if (it.second.category == UPCOMING){
                    upComingAdapter.provided(thisContext, it.first, moviesReqModel, interfaceDialog)
                }
            }
            onError.observe(thisContext) {
                // log messages
                LogUtil.e(TAG, "exceptionResult: $it")

                // hide loading dialog
                interfaceDialog.dismisDialogLoading()

                // show static dialog confirm
                interfaceDialog.showDialogWarningConfirm("Please try again!", it, "OK!")

            }
        }
    }

    private fun setRequest(
        VM: MoviesViewModel,
        binding: ActivityMoviesBinding,
        moviesAdapter: MoviesAdapter,
        topRatedAdapter: MoviesAdapter,
        upComingAdapter: MoviesAdapter
    ) {
        // set loading on ui
        interfaceDialog.showDialogLoading("Loading ...")

        binding.rvPopular.adapter = moviesAdapter
        binding.rvTopRated.adapter = topRatedAdapter
        binding.rvUpComing.adapter = upComingAdapter

        moviesReqModel  = simulationModel(POPULAR)
        VM.doIt(moviesReqModel)

        moviesReqModel  = simulationModel(UPCOMING)
        VM.doIt(moviesReqModel)

        moviesReqModel  = simulationModel(TOP_RATED)
        VM.doIt(moviesReqModel)
    }

    private fun simulationModel(category: String) : MoviesReqModel {
        return MoviesReqModel(
            category = category,
            api_key = "5fb3a869dda2264c448261cd8ade2110"
        )
    }

    override fun onItemClick(v: View?, position: Int, model: MoviesData) {
        val intent = Intent(thisContext, SelectedActivity::class.java)
        intent.putExtra("id", model.id)
        startActivity(intent)
    }
}