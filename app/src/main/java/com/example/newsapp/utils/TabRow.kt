package com.example.newsapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.blue


@Composable
fun CategoriesTabRow(selectTab:(categoryName:String)->Unit){

    var selectedTabIndex = rememberSaveable {

        mutableIntStateOf(0)
    }

    ScrollableTabRow(
        selectedTabIndex =selectedTabIndex.intValue,
        edgePadding = 8.sdp,
        indicator = {},
        divider = {
        },
    ) {


        
        listOfCategories.forEachIndexed {index, tabName->

            Tab(
                selected = selectedTabIndex.intValue == index,
                onClick = {


                    if (selectedTabIndex.intValue != index){

                        selectedTabIndex.intValue = index

                        selectTab(tabName)
                    }

                },
                selectedContentColor = Color.White,
                unselectedContentColor = blue

            ) {

                Text(text = tabName,

                    color = if (selectedTabIndex.intValue == index) Color.White else blue,
                    modifier = if (selectedTabIndex.intValue == index) {

                       Modifier
                            .padding(8.dp)
                            .background(blue, RoundedCornerShape(50.dp))
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                    } else {

                        Modifier
                            .padding(4.sdp)
                            .border(2.sdp, blue, CircleShape)
                            .padding(vertical = 8.sdp, horizontal = 16.sdp)
                    })
            }

        }

    }


}

val listOfCategories = 
    listOf(
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology"
    )