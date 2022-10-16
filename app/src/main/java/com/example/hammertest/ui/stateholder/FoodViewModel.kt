package com.example.hammertest.ui.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hammertest.core.BaseViewModel
import com.example.hammertest.domain.GetAllFoodUseCase
import com.example.hammertest.domain.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val getAllFoodUseCase: GetAllFoodUseCase) :
    BaseViewModel() {
    private val _allFoodResponse = MutableLiveData<List<MealModel>>(null)
    val allFoodResponse: LiveData<List<MealModel>> = _allFoodResponse

    private val _isError = MutableLiveData<Boolean>(null)
    val isError: LiveData<Boolean> = _isError

    fun loadAllFood() {
        viewModelScope.execute(
            onError = {
                _isError.value = true
            }
        ) {
            _allFoodResponse.value = getAllFoodUseCase.getAllFood().body()
        }
    }
}