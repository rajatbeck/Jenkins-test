package com.carousellnews.extension

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

internal fun Fragment.showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

internal fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
