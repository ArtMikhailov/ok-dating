package com.amdev.dating.common.helpers

import com.amdev.dating.common.DatingContext
import com.amdev.dating.common.models.DatingError

fun Throwable.asDatingError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = DatingError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)

fun DatingContext.addError(vararg error: DatingError) = errors.addAll(error)
