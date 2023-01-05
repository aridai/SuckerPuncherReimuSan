package net.aridai.suckerreimu

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Coroutineを起動する。
 */
internal fun launchCoroutine(block: suspend () -> Unit): Job {
    return CoroutineScope(Dispatchers.Main.immediate).launch { block.invoke() }
}
