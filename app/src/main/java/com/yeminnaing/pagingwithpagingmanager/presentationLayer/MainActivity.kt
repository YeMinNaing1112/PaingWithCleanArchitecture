package com.yeminnaing.pagingwithpagingmanager.presentationLayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yeminnaing.pagingwithpagingmanager.presentationLayer.theme.PagingWithPagingManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingWithPagingManagerTheme {
                 MovieListScreen()
            }
        }
    }
}

