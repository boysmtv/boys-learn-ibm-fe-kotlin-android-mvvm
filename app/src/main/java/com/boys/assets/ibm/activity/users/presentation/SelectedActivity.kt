package com.boys.assets.ibm.activity.users.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.boys.assets.ibm.BuildConfig
import com.boys.assets.ibm.activity.users.model.SelectedReqModel
import com.boys.assets.ibm.activity.users.model.SelectedRespModel
import com.boys.assets.ibm.activity.users.vm.SelectedViewModel
import com.boys.assets.ibm.databinding.ActivitySelectedBinding
import com.boys.assets.ibm.helper.InterfaceDialog
import com.boys.assets.ibm.utils.LogUtil
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class SelectedActivity : AppCompatActivity(), SelectedOnClickListener<SelectedRespModel> {

    private val TAG = this::class.java.simpleName
    private val thisContext = this@SelectedActivity

    private lateinit var binding            : ActivitySelectedBinding
    private lateinit var interfaceDialog    : InterfaceDialog

    private lateinit var selectedReqModel   : SelectedReqModel
    private lateinit var selectedRespModel  : SelectedRespModel

    private var idMoviesSelected : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding             = ActivitySelectedBinding.inflate(layoutInflater)

        interfaceDialog     = InterfaceDialog(thisContext)
        selectedReqModel    = SelectedReqModel()
        selectedRespModel   = SelectedRespModel()

        setContentView(binding.root)
        supportActionBar!!.hide()

        val intent = intent
        idMoviesSelected = intent.getIntExtra("id", 0)

        val VM by viewModel<SelectedViewModel>()

        setAddressVM(VM)
        setRequest(VM, binding)
    }
    /**
     * set view model
     */
    private fun setAddressVM(
        VM: SelectedViewModel
    ) {
        with(VM){
            onSuccess.observe(thisContext) {
                setVariables(it)
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
        VM: SelectedViewModel,
        binding: ActivitySelectedBinding
    ) {
        // set loading on ui
        interfaceDialog.showDialogLoading("Loading ...")

        VM.doIt(
            SelectedReqModel(
                id = idMoviesSelected,
                api_key = "5fb3a869dda2264c448261cd8ade2110"
            )
        )
    }

    private fun setVariables(model: SelectedRespModel) {
        interfaceDialog.dismisDialogLoading()

        binding.tvSelectedTitle.text = model.original_title
        binding.tvSelectedDescription.text = model.overview
        binding.tvSelectedBugdet.text = model.budget?.let { getRupiah(it) }

        var tempGenres = ""
        if (model.genres != null){
            if (model.genres!!.size > 0){
                for (i in 0 until model.genres!!.size){
                    tempGenres += model.genres!![i].name + ", "
                }
                tempGenres.dropLast(2)
            }
        }
        binding.tvSelectedGenres.text = tempGenres

        binding.tvSelectedLanguage.text = model.original_language!!.substring(0, 1).toUpperCase() + model.original_language!!.substring(1)

        Glide.with(binding.root).load(BuildConfig.IMAGE_URL_LOW + model.poster_path).into(binding.ivSelectedPoster)
        Glide.with(binding.root).load(BuildConfig.IMAGE_URL_HIGH + model.backdrop_path).into(binding.ivSelectedBackdrop)
    }

    override fun onItemClick(v: View?, position: Int, model: SelectedRespModel) {
        val intent = Intent(thisContext, SelectedActivity::class.java)
        intent.putExtra("id", model.id)
        startActivity(intent)
    }

    private fun getRupiah(price : Long): String {
        val kursIndonesia: DecimalFormat = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val formatRp = DecimalFormatSymbols()
        formatRp.currencySymbol = "Rp. "
        formatRp.monetaryDecimalSeparator = ','
        formatRp.groupingSeparator = '.'
        kursIndonesia.decimalFormatSymbols = formatRp
        return kursIndonesia.format(price)
    }
}