package com.pefdneves.domain.exception

open class PanicModeException(msg: String) : Exception(msg)

class InvalidActionException(msg: String) : PanicModeException(msg)
