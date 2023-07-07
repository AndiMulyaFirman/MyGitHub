package com.project.mygithub.ui.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.mygithub.FollowersItem
import com.project.mygithub.adapter.FollowersAdapter
import com.project.mygithub.databinding.FragmentFollowBinding

class FollowersFragment : Fragment() {

    private val followersViewModel by viewModels<FollowersViewModel>()
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding?.rvFollow?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding?.rvFollow?.addItemDecoration(itemDecoration)

        val username = requireActivity().intent.getStringExtra("USERNAME").toString()

        followersViewModel.getFollowers(username)

        followersViewModel.items.observe(viewLifecycleOwner) { Items ->
            setFragmentData(Items)
        }

        followersViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun setFragmentData(Items: List<FollowersItem>) {
        val adapterMain = FollowersAdapter(Items)
        binding?.rvFollow?.adapter = adapterMain
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
