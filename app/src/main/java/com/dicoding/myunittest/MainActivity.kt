package com.dicoding.myunittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.dicoding.myunittest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityMainbinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainbinding.root)

        mainViewModel = MainViewModel(CuboidModel())

        activityMainbinding.btnSave.setOnClickListener(this)
        activityMainbinding.btnCalculateSurfaceArea.setOnClickListener(this)
        activityMainbinding.btnCalculateCircumference.setOnClickListener(this)
        activityMainbinding.btnCalculateVolume.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val length = activityMainbinding.edtLength.text.toString().trim()
        val width = activityMainbinding.edtWidth.text.toString().trim()
        val height = activityMainbinding.edtHeight.text.toString().trim()

        when {
            TextUtils.isEmpty(length) -> {
                activityMainbinding.edtLength.error = "Field ini tidak boleh kosong"
            }
            TextUtils.isEmpty(width) -> {
                activityMainbinding.edtWidth.error = "Field ini tidak boleh kosong"
            }
            TextUtils.isEmpty(height) -> {
                activityMainbinding.edtHeight.error = "Field ini tidak boleh kosong"
            }

            else -> {
                val valueLength = length.toDouble()
                val valueWidth = width.toDouble()
                val valueHeight = width.toDouble()

                when (v?.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(valueWidth, valueLength, valueHeight)
                        visible()
                    }
                    R.id.btn_calculate_circumference -> {
                        activityMainbinding.tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        activityMainbinding.tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calculate_volume -> {
                        activityMainbinding.tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun visible() {
        activityMainbinding.btnCalculateVolume.visibility = View.VISIBLE
        activityMainbinding.btnCalculateCircumference.visibility = View.VISIBLE
        activityMainbinding.btnCalculateSurfaceArea.visibility = View.VISIBLE
        activityMainbinding.btnSave.visibility = View.GONE
    }

    private fun gone() {
        activityMainbinding.btnCalculateVolume.visibility = View.GONE
        activityMainbinding.btnCalculateCircumference.visibility = View.GONE
        activityMainbinding.btnCalculateSurfaceArea.visibility = View.GONE
        activityMainbinding.btnSave.visibility = View.VISIBLE
    }
}