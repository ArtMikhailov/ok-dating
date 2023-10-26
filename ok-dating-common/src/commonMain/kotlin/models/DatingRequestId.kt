package com.amdev.dating.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class DatingRequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = DatingRequestId("")
    }
}
