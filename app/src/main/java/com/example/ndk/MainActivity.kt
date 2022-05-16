package com.example.ndk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ndk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     val TAG = "ndk"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()
        binding.sampleCppTestText.text = isIntCpp().toString()

        if (isBooleanCpp()){
            Toast.makeText(this,TAG,Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * A native method that is implemented by the 'ndk' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun getmyJNTValue() : String

    external fun isIntCpp() : Int

    external fun isBooleanCpp() : Boolean

    companion object {
        // Used to load the 'ndk' library on application startup.
        init {
            System.loadLibrary("ndk")
        }
    }
}