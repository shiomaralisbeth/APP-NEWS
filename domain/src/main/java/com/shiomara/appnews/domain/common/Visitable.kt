package com.shiomara.appnews.domain.common

interface Visitable {
    fun type(typeFactory: TypeFactory): Int
}