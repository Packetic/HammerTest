package com.example.hammertest.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.example.hammertest.R
import com.example.hammertest.databinding.FragmentMenuBinding
import com.example.hammertest.ui.adapters.BannerListAdapter
import com.example.hammertest.ui.adapters.FoodListAdapter
import com.example.hammertest.ui.stateholder.FoodViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FoodViewModel>()
    private lateinit var bannerListAdapter: BannerListAdapter
    private lateinit var foodListAdapter: FoodListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadAllFood()
        observeViewModel()
        setupBannerRecyclerView()
        setupFoodRecyclerView()

        // Если нажать на ретрай, снова грузим ресайклер
        binding.retry.setOnClickListener {
            viewModel.loadAllFood()
        }

        binding.foodRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) binding.cardView.cardElevation = 10f
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBannerRecyclerView() {
        bannerListAdapter = BannerListAdapter(
            listOf(
                R.drawable.pizza_banner.toDrawable(),
                R.drawable.pizza_banner.toDrawable(),
                R.drawable.pizza_banner.toDrawable(),
                R.drawable.pizza_banner.toDrawable()
            )
        )
        binding.bannerRv.adapter = bannerListAdapter
        binding.bannerRv.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun setupFoodRecyclerView() {
        foodListAdapter = FoodListAdapter()
        binding.foodRv.apply {
            adapter = foodListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeViewModel() {
        viewModel.allFoodResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                foodListAdapter.submitList(it)
            }
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.progressBar.visibility = View.GONE
                binding.foodRv.visibility = View.GONE
                binding.somethingWrong.visibility = View.VISIBLE
                binding.retry.visibility = View.VISIBLE
            }
        }
        viewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.foodRv.visibility = View.VISIBLE
                binding.retry.visibility = View.GONE
                binding.somethingWrong.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        }
    }

}