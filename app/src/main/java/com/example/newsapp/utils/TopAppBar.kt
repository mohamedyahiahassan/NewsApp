package com.example.newsapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import com.example.newsapp.Screens
import com.example.newsapp.ui.theme.blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(currentDestination: NavDestination?,
                  scrollBehavior: TopAppBarScrollBehavior,
                  onBackClicked:()->Unit){

    val backButtonShown = rememberSaveable {
        mutableStateOf(false)
    }

    val showShareButton = rememberSaveable {
        mutableStateOf(false)
    }

    val title = rememberSaveable {
        mutableStateOf("News App")
    }

    val isScrollableTopAppBar = rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = currentDestination?.route) {

        when(currentDestination?.route){

            Screens.HomeScreen.route -> {

                backButtonShown.value = false

                isScrollableTopAppBar.value = true

                showShareButton.value = false

                title.value = "News App"
            }

            Screens.ArticleDetails.route ->{


                backButtonShown.value = true

                isScrollableTopAppBar.value = false

                showShareButton.value = true

                title.value = ""
            }



        }


    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title.value,
                color = blue,
                fontWeight = FontWeight.Bold)
                },
        navigationIcon = {

            if (backButtonShown.value){

                IconButton(
                    onClick = {
                        onBackClicked()
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription ="back button" )
                }
            }
        },
        scrollBehavior =  if (isScrollableTopAppBar.value) scrollBehavior else TopAppBarDefaults.pinnedScrollBehavior()
    )

}