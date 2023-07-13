package com.example.memesapi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var memesListResponse:List<memesList> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getMemesInstance() {
        viewModelScope.launch {
            val apiServ = ApiService.getInstance()
            try {
                val memesList = apiServ.getMemes()
                memesListResponse = memesList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}