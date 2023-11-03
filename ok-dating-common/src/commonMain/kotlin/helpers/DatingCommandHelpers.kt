package com.amdev.dating.common.helpers

import com.amdev.dating.common.DatingContext
import com.amdev.dating.common.models.DatingCommand

fun DatingContext.isUpdatableCommand() =
    this.command in listOf(DatingCommand.CREATE, DatingCommand.UPDATE, DatingCommand.DELETE)
