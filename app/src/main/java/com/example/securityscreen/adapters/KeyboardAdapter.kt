package com.example.securityscreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.securityscreen.ClickListener
import com.example.securityscreen.R
import com.example.securityscreen.databinding.DifferentKeysBinding
import com.example.securityscreen.databinding.KeyboardItemBinding
import com.example.securityscreen.enums.Keys

class KeyboardAdapter(
    private var elements: MutableList<Int>,
    private val clickListener: ClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_NUMBER = 0
        private const val ITEM_ICON = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_NUMBER) {
            NumberViewHolder(
                KeyboardItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            IconViewHolder(
                DifferentKeysBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NumberViewHolder -> holder.bind()
            is IconViewHolder -> holder.bind()
        }
    }

    override fun getItemCount() = elements.size

    override fun getItemViewType(position: Int): Int {
        val element = elements[position]
        return if (element == Keys.FINGERPRINT.position || element == Keys.BACK.position)
            ITEM_ICON
        else
            ITEM_NUMBER
    }

    inner class NumberViewHolder(private val binding: KeyboardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var element = 0
        fun bind() {
            if (adapterPosition == Keys.ZERO.position) {
                binding.keyId.text = "0"
            }
            element = elements[adapterPosition]
            binding.keyId.text = element.toString()
            binding.root.setOnClickListener {
                clickListener.onNumberClick(element)
            }
        }
    }

    inner class IconViewHolder(private val binding: DifferentKeysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var element = 0
        fun bind() {
            element = elements[adapterPosition]
            if (element == Keys.FINGERPRINT.position) {
                binding.keyLogo.setImageResource(R.drawable.ic_touch__id_1)
                binding.root.setOnClickListener {
                    clickListener.onFingerPrintClick()
                }
            } else {
                binding.keyLogo.setImageResource(R.drawable.ic_back)
                binding.root.setOnClickListener {
                    clickListener.onBackClick()
                }
            }
        }
    }
}