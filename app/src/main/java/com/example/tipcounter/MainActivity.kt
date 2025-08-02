package com.example.tipcounter

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipcounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        var percentage = 5
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ETNUserPercentage.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.RBUSERP.isChecked = true
            }
        }


        binding.BtCount.setOnClickListener {
            binding.TvTip.visibility = View.VISIBLE
            binding.TvAllStats.visibility = View.VISIBLE

            when(binding.RadioG.checkedRadioButtonId){
                binding.RB5P.id -> percentage = 5
                binding.RB10P.id -> percentage = 10
                binding.RB15P.id -> percentage = 15
                binding.RBUSERP.id -> percentage = binding.ETNUserPercentage.text.toString().toInt()
            }

            val conv = conversion(percentage.toDouble())
            val usersBill = binding.ETNUserInput.text.toString().toDouble()
            val result = usersBill * conv
            binding.TvTip.text = "Чаевые: $result"

            binding.TvAllStats.text = " Сумма счёта: $usersBill \n\n Процент чаевых: $percentage% \n\n Сумма чаевых: $result \n\n Общий счёт с чаевыми: ${usersBill + result}"

        }

    }
    fun conversion(Pers: Double): Double{
        val finishedVal = Pers / 100
        return finishedVal
    }
}