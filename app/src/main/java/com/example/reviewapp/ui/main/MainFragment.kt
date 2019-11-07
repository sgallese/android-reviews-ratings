package com.example.reviewapp.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.reviewapp.R
import com.example.reviewapp.models.*
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var spinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        spinner = library_select_spinner

        context?.let {

            ArrayAdapter.createFromResource(
                it,
                R.array.select_library,
                android.R.layout.simple_spinner_item
            )
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter
                }
        }

        launch_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {


        val selectedLibrary = when (spinner.selectedItem) {
            getString(R.string.amplify_library) -> ReviewLibrary.AmplifyLibrary
            getString(R.string.five_stars_library) -> ReviewLibrary.FiveStarsLibrary
            getString(R.string.material_app_rating_library) -> ReviewLibrary.MaterialAppRatingLibrary
            getString(R.string.rate_library) -> ReviewLibrary.RateLibrary
            getString(R.string.rate_this_app_library) -> ReviewLibrary.RateThisAppLibrary
            else -> ReviewLibrary.AmplifyLibrary
        }
        val config = ReviewLibraryConfig(
            selectedLibrary,
            true,
            "Rate our app",
            "Let us know what you think of our app"
        )

        navController.navigate(
            MainFragmentDirections.actionMainFragmentToReviewFragment(
                FiveStarsLibraryData, config
            )
        )
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
            }
    }
}
