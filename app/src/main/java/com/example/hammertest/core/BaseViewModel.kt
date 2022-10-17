package com.example.hammertest.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Базовая вью модель, от которой надо наследоваться, если нам надо обрабатывать ошибки
abstract class BaseViewModel : ViewModel() {

    protected fun CoroutineScope.execute(
        onSuccess: (() -> Unit)? = null,
        onError: (() -> Unit)? = null,
        function: suspend () -> Unit,
    ) {
        launch(Dispatchers.IO) {
            runCatching {
                function()
            }
                .onSuccess {
                    withContext(Dispatchers.Main) {
                        onSuccess?.invoke()
                    }
                }
                .onFailure { error ->
                    error.printStackTrace()
                    withContext(Dispatchers.Main) {
                        onError?.invoke()
                    }
                }
        }
    }
}