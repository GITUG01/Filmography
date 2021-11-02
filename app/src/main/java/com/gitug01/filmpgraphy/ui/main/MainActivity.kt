package com.gitug01.filmpgraphy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.ui.screens.MainScreen

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.fragments_container, MainScreen(), false)

    }

    private fun replaceFragment(@IdRes containerViewId: Int,
                                @NonNull fragment: Fragment,
                                addToBackStack: Boolean){
        when(addToBackStack){
            false -> supportFragmentManager.beginTransaction().
            replace(containerViewId, fragment).commit()
            true -> supportFragmentManager.beginTransaction().
            replace(containerViewId, fragment).addToBackStack(null).commit()
        }
    }
}