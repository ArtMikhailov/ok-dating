package com.amdev.dating.mappers.v1.exceptions

import com.amdev.dating.common.models.DatingCommand

class UnknownDatingCommand(command: DatingCommand) : Throwable("Wrong command $command at mapping toTransport stage")
