package com.pefdneves.ui.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pefdneves.ui.actions.databinding.FragmentActionsBinding

class ActionsFragment : Fragment() {

    private lateinit var binding: FragmentActionsBinding

    private val viewModel: ActionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentActionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun option1Clicked() {

    }

}