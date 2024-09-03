package com.example.newsapp

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.utils.CategoriesTabRow
import com.example.newsapp.utils.NewsTopAppBar
import com.example.newsapp.utils.sdp

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AppNavigationContent(){

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            NewsTopAppBar(currentDestination,scrollBehavior){

                navController.navigateUp()
            }
        }
    ) {

        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.route,
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ){

            composable(Screens.HomeScreen.route){

                val viewModel = hiltViewModel<NewsViewModel>()
                HomeNewsContent(viewModel){

                    navController.navigate(Screens.ArticleDetails.route)
                }
                
            }

            composable(Screens.ArticleDetails.route){

                val parentEntry = remember(it) {
                    navController.getBackStackEntry(Screens.HomeScreen.route)
                }

                val viewModel = hiltViewModel<NewsViewModel>(parentEntry)
                viewModel.selectedNews?.let { it1 -> ArticlesDetailsContent(it1) }
            }

        }
    }


}


sealed class Screens(val route:String){

    data object HomeScreen:Screens("home_screen")
    data object ArticleDetails:Screens("article_details")


}