package com.afurkantitiz.foodapp.ui.restaurantdetail_foods

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.foodapp.data.entity.food.Ingredient
import com.afurkantitiz.foodapp.databinding.FragmentFoodAddBinding
import com.afurkantitiz.foodapp.ui.home.RestaurantsFragmentDirections
import com.afurkantitiz.foodapp.utils.Resource
import com.afurkantitiz.foodapp.utils.gone
import com.afurkantitiz.foodapp.utils.show
import com.google.android.flexbox.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodAddFragment(restaurantId: String) : BottomSheetDialogFragment() {
    private var _binding: FragmentFoodAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var ingredientsList: ArrayList<Ingredient>
    private lateinit var ingredientAdapter: IngredientsAdapter
    private lateinit var layoutManager: FlexboxLayoutManager
    private var restId = restaurantId

    private val viewModel: DetailRestaurantFoodsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeIngredients()
        addIngredients()
        onClickListener()
    }

    private fun onClickListener() {
        binding.addMealButton.setOnClickListener {
            addMeal()
        }

        binding.addMealIngredientLogo.setOnClickListener {
            addIngredients()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addIngredients() {
        if (!binding.mealIngredientsEditText.text.isNullOrEmpty()) {
            ingredientsList.add(
                Ingredient(
                    binding.mealIngredientsEditText.text.toString(),
                    true
                )
            )
            ingredientAdapter.notifyDataSetChanged()
            binding.mealIngredientsEditText.text!!.clear()
        }
    }

    private fun addMeal() {
        val ingredients: ArrayList<String> = arrayListOf()
        val name = binding.mealNameEditText.editText?.text.toString()
        val image = binding.mealImageLayout.editText?.text.toString()
        val description = binding.mealDescriptionLayout.editText?.text.toString()
        val price = binding.mealPriceEditText.editText?.text.toString().toDouble()

        for (ingredient in ingredients) {
            ingredients.add(ingredient)
        }

        viewModel.addMeal(
            restId,
            name,
            image,
            price,
            ingredients as List<String>,
            description
        ).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()
                    this.dismiss()
                    val action =
                        DetailRestaurantFoodsFragmentDirections.actionDetailRestaurantFragmentSelf(
                            restId
                        )
                    findNavController().navigate(action)
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
                    Log.v("restId", "$it")
                }
            }
        })
    }

    private fun initializeIngredients() {
        ingredientsList = arrayListOf()
        ingredientAdapter = IngredientsAdapter(ingredientsList)

        layoutManager = FlexboxLayoutManager(activity)
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        layoutManager.alignItems = AlignItems.FLEX_START

        ingredientAdapter.addListener(object : IIngredientOnClick {
            @SuppressLint("NotifyDataSetChanged")
            override fun onIngredientClickListener(ingredient: Ingredient, position: Int) {
                ingredientsList.removeAt(position)
                ingredientAdapter.notifyDataSetChanged()
            }
        })

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = ingredientAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}