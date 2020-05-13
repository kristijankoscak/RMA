package hr.ferit.kristijankoscak.birdcounter_db_vm_ld

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BirdCounterViewModel : ViewModel() {
    var counter : MutableLiveData<Int> = MutableLiveData<Int>()
    var color: MutableLiveData<Int> = MutableLiveData<Int>()
}