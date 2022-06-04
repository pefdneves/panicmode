package com.pefdneves.ui.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pefdneves.ui.actions.databinding.FloatingActionMenuBinding
import com.pefdneves.ui.actions.databinding.FragmentActionsBinding

class ActionsFragment : Fragment() {

    private lateinit var binding: FragmentActionsBinding

    private val viewModel: ActionsViewModel by viewModels()

    private var isFabMenuVisible = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentActionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutFab.initFabMenu()
    }

    private fun FloatingActionMenuBinding.initFabMenu() {
        isFabMenuVisible = false

        fabAddAction.setOnClickListener {
            if (!isFabMenuVisible) {
                fabSendSms.apply {
                    isVisible = true
                    show()
                }
                fabDeleteFolder.apply {
                    isVisible = true
                    show()
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
        }
    }
}
