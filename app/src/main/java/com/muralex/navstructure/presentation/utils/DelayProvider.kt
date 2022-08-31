package com.muralex.navstructure.presentation.utils

import kotlinx.coroutines.delay

interface DelayProvider {
    suspend fun delayTransition()

    class Base : DelayProvider {
        override suspend fun delayTransition() {
            delay(120)
        }
    }

}