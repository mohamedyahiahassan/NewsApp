package com.example.newsapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavDestination
import com.example.newsapp.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(currentDestination: NavDestination?,
                  scrollBehavior: TopAppBarScrollBehavior,
                  onBackClicked:()->Unit){

    val backButtonShown = rememberSaveable {
        mutableStateOf(false)
    }

    val isScrollableTopAppBar = rememberSaveable {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = currentDestination?.route) {

        when(currentDestination?.route){

            Screens.HomeScreen.route -> {

                backButtonShown.value = false

                isScrollableTopAppBar.value = true
            }

            Screens.ArticleDetails.route ->{


                backButtonShown.value = true

                isScrollableTopAppBar.value = false
            }



        }


    }

    CenterAlignedTopAppBar(
        title = {
            Text(text = "News App")
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