package com.pefdneves.domain

open class PanicModeException(msg: String) : Exception(msg)

class InvalidActionException(msg: String) : PanicModeException(msg)
