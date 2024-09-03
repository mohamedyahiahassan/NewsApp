package com.example.newsapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.example.newsapp.ui.theme.blue


@Composable
fun LoadingDialog(){

        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .height(100.sdp)
                    .width(100.sdp)
                    .background(Color.White, shape = RoundedCornerShape(8.sdp)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = blue,
                    modifier = Modifier
                        .width(35.sdp)
                        .height(35.sdp)
                )
            }
        }
}