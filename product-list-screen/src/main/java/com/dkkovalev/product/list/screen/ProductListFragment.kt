package com.dkkovalev.product.list.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkkovalev.product.list.screen.databinding.ProductListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private val viewModel by viewModels<ProductListViewModel>()
    private val compositeDisposable = CompositeDisposable()

    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    private val productAdapter = ProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ProductListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable.add(
            viewModel.productsObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    ::populateList,
                    Throwable::printStackTrace
                )
        )


        with(binding) {

            updateList.setOnClickListener {
                viewModel.loadProducts()
            }

            productsList.apply {
                adapter = productAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.loadProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.cleanup()
        compositeDisposable.clear()
        _binding = null
    }

    private fun populateList(products: List<Product>) {
        productAdapter.submitList(products)
    }
}