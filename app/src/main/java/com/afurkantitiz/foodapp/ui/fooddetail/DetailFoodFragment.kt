package com.afurkantitiz.foodapp.ui.fooddetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.foodapp.databinding.FragmentDetailMealBinding
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFoodFragment : Fragment() {
    private var _binding: FragmentDetailMealBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFoodFragmentArgs by navArgs()
    private val viewModel: DetailFoodViewModel by viewModels()

    private var adapter: DetailFoodAdapter = DetailFoodAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ingredientsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        getMealDetails()
    }

    private fun getMealDetails() {
        viewModel.getMealDetails(args.mealId).observe(viewLifecycleOwner, { it ->
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()

                    val meal = it.data!!.data
                    viewModel.meal = meal

                    Glide
                        .with(requireContext())
                        .load(meal.image)
                        .into(binding.imageView)

                    binding.mealNameTextView.text = meal.name
                    binding.mealDescription.text = meal.description
                    binding.mealPrice.text = meal.price


                    adapter.setIngredientList(meal.ingredients)
                    binding.ingredientsRecyclerView.adapter = adapter

                    binding.backButton.setOnClickListener {
                        it.findNavController().popBackStack()
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}