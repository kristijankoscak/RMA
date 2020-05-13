package hr.ferit.kristijankoscak.birdcounter_db_vm_ld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.ferit.kristijankoscak.birdcounter_db_vm_ld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var birdViewModel : BirdCounterViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        birdViewModel = ViewModelProvider(this).get(BirdCounterViewModel::class.java)
        binding.bird = birdViewModel
        setUpUI()
    }
    private fun setUpUI(){
        setButtons()
        setInitialValues()
        setObservers()
    }
    private fun setObservers(){
        birdViewModel.counter.observe(this, Observer { binding.invalidateAll() })
        birdViewModel.color.observe(this, Observer { binding.invalidateAll() })
    }
    private fun setInitialValues(){
        binding.apply {
            if( bird?.counter?.value == null) {
                bird?.counter?.value = 0
            }
            if(bird?.color?.value==null){
                bird?.color?.value=R.color.transparent
            }
        }
    }
    private fun setButtons(){
        binding.apply {
            redColoredBtn.setOnClickListener { handleColorClick(R.color.btnRed);println("KLIK") }
            blueColoredBtn.setOnClickListener { handleColorClick(R.color.btnBlue) }
            greenColoredBtn.setOnClickListener { handleColorClick(R.color.btnGreen) }
            yellowColoredBtn.setOnClickListener { handleColorClick(R.color.btnYellow) }
            resetColorBtn.setOnClickListener { resetColor() }
            resetCounterBtn.setOnClickListener { resetCounter() }
        }
    }
    private fun resetCounter(){
        binding.apply {
            bird?.counter?.value = 0
        }
    }
    private fun resetColor(){
        binding.apply {
            bird?.color?.value  = R.color.transparent
        }
    }
    private fun handleColorClick(color:Int){
        binding.apply {
            bird?.counter?.value = (bird?.counter?.value!! + 1)
            bird?.color?.value  =  color
        }
    }

}
