package com.kriya.biosys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kriya.biosys.navigation.KriyaNavHost
import com.kriya.biosys.ui.theme.KriyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KriyaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    KriyaNavHost()
                }
            }
        }
    }
}
