package com.pefdneves.ui.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pefdneves.ui.actions.databinding.FloatingActionMenuBinding
import com.pefdneves.ui.actions.databinding.FragmentActionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActionsFragment : Fragment() {

    private lateinit var binding: FragmentActionsBinding

    private val viewModel: ActionsViewModel by viewModels()

    private lateinit var listAdapter: ActionsAdapter

    private var isFabMenuVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActionsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupListAdapter()
        binding.layoutFab.initFabMenu()
    }

    private fun observeViewModel() {
        viewModel.deleteActionPopup.observe(
            viewLifecycleOwner
        ) { deleteFunction ->
            deleteFunction.getContentIfNotHandled()?.also {
                showDeleteActionPopup(it)
            }
        }
    }

    private fun showDeleteActionPopup(deleteFunction: () -> Unit) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(context?.getString(R.string.popup_delete_confirm))
            .setPositiveButton(context?.getString(R.string.yes)) { dialog, _ ->
                deleteFunction()
                dialog.dismiss()
            }
            .setNegativeButton(context?.getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun setupListAdapter() {
        binding.viewmodel?.also {
            listAdapter = ActionsAdapter(viewModel)
            binding.actionsList.adapter = listAdapter
        }
    }

    private fun FloatingActionMenuBinding.initFabMenu() {
        isFabMenuVisible = false

        fabSendSms.setOnClickListener {
            viewModel.option1Clicked()
            fabAddAction.callOnClick()
        }

        fabDeleteFolder.setOnClickListener {
            viewModel.option2Clicked()
            fabAddAction.callOnClick()
        }

        fabAddAction.setOnClickListener {
            if (!isFabMenuVisible) {
                fabSendSms.apply {
                    show()
                    isVisible = true
                }
                fabDeleteFolder.apply {
                    show()
                    isVisible = true
                }
                isFabMenuVisible = true
            } else {
                fabSendSms.apply {
                    hide()
                    isVisible = false
                }
                fabDeleteFolder.apply {
                    hide()
                    isVisible = false
                }
                isFabMenuVisible = false
            }
            binding.layoutFab.invalidateAll()
        }
    }
}
