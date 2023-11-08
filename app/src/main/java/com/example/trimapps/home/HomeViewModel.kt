package com.example.trimapps.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.trimapps.database.SettingPreferences
import com.example.trimapps.home.remote.ApiClient
import com.example.trimapps.utils.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(private val preferences: SettingPreferences) : ViewModel() {

    val resultUser = MutableLiveData<Result>()

    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun getUser() {
        viewModelScope.launch {
            flow {
                val response = ApiClient
                    .githubService
                    .getUserGithub()

                emit(response)
            }.onStart {
                resultUser.value = Result.Loading(false)
            }.onCompletion {
                resultUser.value = Result.Loading(false)
            }.catch { error ->
                error.printStackTrace()
                resultUser.value = Result.Error(error)
            }.collect {
                resultUser.value = Result.Success(it)
            }
        }
    }

    fun getUser(query: String) {
        viewModelScope.launch {
            flow {
                val response = ApiClient
                    .githubService
                    .searchUserGithub(
                        mapOf(
                            "q" to "in:header $query",
                            "per_page" to 10
                        )
                    )
                emit(response)
            }.onStart {
                resultUser.value = Result.Loading(false)
            }.onCompletion {
                resultUser.value = Result.Loading(false)
            }.catch { error ->
                error.printStackTrace()
                resultUser.value = Result.Error(error)
            }.collect {
                resultUser.value = Result.Success(it.items)
            }
        }
    }

    class Factory(private val preferences: SettingPreferences) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            HomeViewModel(preferences) as T
        }
}