package com.hakandemir.borsa.viewmodel

import android.content.IntentSender.OnFinished
import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hakandemir.borsa.model.Borsa

class MainViewModel : ViewModel() {
    //live data create
    val borsaDate = MutableLiveData<Borsa>()
    val borsaLoad = MutableLiveData<Boolean>()
    val borsaError = MutableLiveData<Boolean>()

    init {
        val timer = object : CountDownTimer (5000,1000) {
            // when it starts
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished.toInt() == 3000) {
                    val deger = 5000
                }
            }
            //when it finish
            override fun onFinish() {
                borsaLoad.value = false
                val borsa = Borsa("BorsaApp", "borsaImageUrl")
                //servisten veri geldi demek- fake veri alıyoruz
                borsaDate.value = borsa
            }
        }
        getDataFromService(timer)
    }

    private fun getDataFromService(timer: CountDownTimer){
        borsaLoad.value = true
        timer.start()
    }

}