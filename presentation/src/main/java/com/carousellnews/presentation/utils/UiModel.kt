package com.carousellnews.presentation.utils

interface UiModel

open class UiAwareModel : UiModel {
    var isRedelivered: Boolean = false
}