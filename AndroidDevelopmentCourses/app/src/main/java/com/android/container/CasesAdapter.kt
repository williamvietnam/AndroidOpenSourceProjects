package com.android.container

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.databinding.ItemCaseBinding

class CasesAdapter(
    private val cases: List<Case>?,
    private val callback: CaseCallBack
) : Adapter<CasesAdapter.CasesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCaseBinding = ItemCaseBinding.inflate(inflater, parent, false)
        return CasesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CasesViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        if (cases != null) {
            return cases.size
        }
        return 0
    }

    inner class CasesViewHolder(
        private val binding: ItemCaseBinding,
    ) : ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val case = cases?.get(position)
            if (case != null) {
                this.binding.text.text = case.text
                this.binding.root.setOnClickListener {
                    callback.onCaseClicked(case)
                    Log.d(TAG, case.text)
                }
            }
        }
    }

    interface CaseCallBack {
        fun onCaseClicked(case: Case)
    }

    companion object {
        const val TAG = "CasesAdapter.CasesViewHolder"
    }
}