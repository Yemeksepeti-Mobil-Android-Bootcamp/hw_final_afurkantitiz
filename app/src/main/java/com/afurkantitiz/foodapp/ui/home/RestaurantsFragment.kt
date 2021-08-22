package com.afurkantitiz.foodapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.R
import com.afurkantitiz.foodapp.data.entity.restaurant.Restaurant
import com.afurkantitiz.foodapp.databinding.FragmentRestaurantsBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.hide
import com.afurkantitiz.foodapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantsFragment : Fragment(), ICategoriesOnClick {
    private var _binding: FragmentRestaurantsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RestaurantsViewModel by viewModels()
    private var restaurantsAdapter = RestaurantsAdapter()
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var currentRole: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantsBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {
        categoriesAdapter = CategoriesAdapter(viewModel.getCategories(), requireContext())
        categoriesAdapter.addListener(this)

        binding.restaurantRecyclerView.layoutManager = GridLayoutManager(context, 2)

        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.setHasFixedSize(true)
        binding.categoryRecyclerView.adapter = categoriesAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserForAPI()
        onSearchViewListener()
        onClickListener()
        getRestaurantsForAPI()
    }

    private fun onClickListener() {
        binding.restaurantAddButton.setOnClickListener {
            val restaurantAddFragment = RestaurantAddFragment()
            restaurantAddFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.ThemeOverlay_Demo_BottomSheetDialog
            )
            restaurantAddFragment.show(
                requireActivity().supportFragmentManager,
                "RestaurantAddBottomSheet"
            )
        }
    }

    private fun onSearchViewListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filterList = viewModel.searchViewForRestaurants(query)
                setRestaurantsForRestaurantAdapter(filterList)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val filterList = viewModel.searchViewForRestaurants(query)
                setRestaurantsForRestaurantAdapter(filterList)
                return true
            }
        })
    }

    private fun getRestaurantsForAPI() {
        viewModel.getRestaurants().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    binding.lottieLoading.show()
                    binding.lottieLoading.playAnimation()
                    binding.screenLayout.gone()
                }
                Resource.Status.SUCCESS -> {
                    binding.lottieLoading.cancelAnimation()
                    binding.lottieLoading.gone()
                    binding.screenLayout.show()
                    viewModel.restaurantList = response.data?.restaurantList
                    setRestaurantsForRestaurantAdapter(viewModel.restaurantList)
                }
                Resource.Status.ERROR -> {
                    binding.lottieLoading.gone()
                    Toast.makeText(
                        requireContext(),
                        "Network Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun getRestaurantByCategories(currentPosition: Int) {
        if (currentPosition == 0) {
            getRestaurantsForAPI()
        } else {
            viewModel.getRestaurantByCuisine(viewModel.getCategories()[currentPosition].categoryName)
                .observe(viewLifecycleOwner, { response ->
                    viewModel.restaurantList = response.data?.restaurantList
                    setRestaurantsForRestaurantAdapter(response.data?.restaurantList)
                })
        }
    }

    private fun setRestaurantsForRestaurantAdapter(restaurantList: List<Restaurant>?) {
        restaurantsAdapter.setData(restaurantList)
        binding.restaurantRecyclerView.adapter = restaurantsAdapter
    }

    private fun getUserForAPI() {
        viewModel.getUser().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.v("getUser", "Loading")
                }
                Resource.Status.SUCCESS -> {
                    val userData = it.data!!.user
                    currentRole = userData!!.role

                    isAdmin(currentRole)
                }
                Resource.Status.ERROR -> {
                    Log.v("getUser", "Error")
                }
            }
        })
    }

    private fun isAdmin(currentRole: String) {
        if(currentRole == "admin"){
            binding.restaurantAddButton.show()
        }else {
            binding.restaurantAddButton.hide()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        categoriesAdapter.removeListeners()
    }

    override fun onClick(position: Int) {
        getRestaurantByCategories(position)
    }
}