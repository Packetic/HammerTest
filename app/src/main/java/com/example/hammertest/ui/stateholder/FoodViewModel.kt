package com.example.hammertest.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hammertest.core.BaseViewModel
import com.example.hammertest.domain.usecase.GetAllFoodUseCase
import com.example.hammertest.domain.model.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// Наследование от BaseViewModel, чтобы хендлить ошибки
@HiltViewModel
class FoodViewModel @Inject constructor(private val getAllFoodUseCase: GetAllFoodUseCase) :
    BaseViewModel() {
    private val _allFoodResponse = MutableLiveData<List<MealModel>>(null)
    val allFoodResponse: LiveData<List<MealModel>> = _allFoodResponse

    private val _isError = MutableLiveData<Boolean>(null)
    val isError: LiveData<Boolean> = _isError

    private val _isSuccess = MutableLiveData<Boolean>(null)
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun loadAllFood() {
        viewModelScope.execute(
            onSuccess = {
                _isSuccess.value = true
            },
            onError = {
                _isError.value = true
            }
        ) {
            _allFoodResponse.postValue(getAllFoodUseCase.getAllFood().body()?.meals)
        }
    }
}