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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.green


@Composable
fun CategoriesTabRow(selectTab:(categoryName:String)->Unit){

    var selectedTabIndex = rememberSaveable {

        mutableIntStateOf(0)
    }

    ScrollableTabRow(
        selectedTabIndex =selectedTabIndex.intValue,
        edgePadding = 8.sdp,
        indicator = {},
        divider = {},
    ) {


        
        listOfCategories.forEachIndexed {index, tabName->

            Tab(
                selected = selectedTabIndex.intValue == index,
                onClick = {


                    if (selectedTabIndex.intValue != index){

                        selectedTabIndex.intValue = index

                        selectTab(tabName)
                    }



                }

            ) {

                Text(text = tabName,

                    color = if (selectedTabIndex.intValue == index) Color.White else Color.Black,
                    modifier = if (selectedTabIndex.intValue == index) {

                        Modifier
                            .padding(4.sdp)
                            .background(green, RoundedCornerShape(50.sdp))
                            .padding(vertical = 8.sdp, horizontal = 16.sdp)
                    } else {

                        Modifier
                            .padding(4.sdp)
                            .border(2.sdp, green, CircleShape)
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