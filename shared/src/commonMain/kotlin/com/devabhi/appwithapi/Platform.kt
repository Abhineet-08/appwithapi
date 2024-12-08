package com.devabhi.appwithapi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform