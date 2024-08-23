package com.ierusalem.kadrlar.core.utils

import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.shortToast(text: String, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(requireContext(), text, duration).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}