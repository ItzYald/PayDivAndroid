package com.example.doljnikprod.viewModel

import com.example.doljnikprod.model.addCheck
import com.example.doljnikprod.model.AddCheckData

class AddCheckViewModel : IObserver {
    override fun update(a: Any) {
        val data = (a as? AddCheckData) ?: return

        addCheck(data)
    }
}

