package com.example.securityscreen

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.securityscreen.adapters.KeyboardAdapter
import com.example.securityscreen.databinding.SecurityFragmentBinding

class SecurityFragment : Fragment() {

    private var _binding: SecurityFragmentBinding? = null
    private lateinit var adapter: KeyboardAdapter
    private val binding get() = _binding!!

    private var passcode = mutableListOf<Int>()
    private var stepCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SecurityFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initKeys()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initKeys() {
        val keys = mutableListOf<Int>()
        for (i in 1..12) {
            keys.add(i)
        }
        adapter = KeyboardAdapter(keys, object : ClickListener {
            override fun onNumberClick(index: Int) {
                stepCount++
                passcode.add(index)
                d("click", "$stepCount")
                d("click", "$passcode")
                if (stepCount == 4) {
                    binding.idInstruction.text = "Repeat passcode"
                }
            }

            override fun onFingerPrintClick() {
                d("click", "FINGERPRINT")
            }

            override fun onBackClick() {
                passcode.removeAt(stepCount - 1)
                stepCount--
                d("click", "$stepCount")
                d("click", "$passcode")
            }
        })
        binding.keysRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.keysRecycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}