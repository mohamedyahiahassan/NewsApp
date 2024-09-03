package com.example.newsapp

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.example.domain.model.ArticlesItem
import com.example.newsapp.utils.CategoriesTabRow
import com.example.newsapp.utils.LoadingDialog
import com.example.newsapp.utils.listOfCategories
import com.example.newsapp.utils.sdp
import java.time.Instant
import java.time.ZoneId
import java.util.Date

@Composable
fun HomeNewsContent(
    viewModel: NewsViewModel,
    readNews:()->Unit
){

    val state = viewModel.states.collectAsState()

    /*
    LaunchedEffect(key1 = Unit) {

        viewModel.doAction(NewsContract.Action.LoadNewsList(listOfCategories[0]))
    }

     */


    Column(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {

        CategoriesTabRow {

            viewModel.doAction(NewsContract.Action.LoadNewsList(it))
        }


        when (val s = state.value) {

            NewsContract.States.Initial -> {

                viewModel.doAction(NewsContract.Action.LoadNewsList(listOfCategories[0]))
            }
            is NewsContract.States.Error -> {


            }

            NewsContract.States.Loading -> {

                LoadingDialog()
            }
            is NewsContract.States.Success -> {

                if (s.articlesList!= null) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(20.sdp),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.sdp, end = 16.sdp)
                    ) {

                        item {
                            Spacer(modifier = Modifier.height(1.sdp))
                        }
                        itemsIndexed(s.articlesList) { index, article ->

                            NewsCard(article){

                                viewModel.doAction(NewsContract.Action.OpenNews(article!!))

                               readNews()
                            }
                        }
                    }
                }

            }


        }

    }

}



@Composable
fun NewsCard(article:ArticlesItem?,openArticle:()->Unit){

    Card(
        onClick = {

            openArticle()

        },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth(1f)


    ) {

        AsyncImage(
            model = article?.urlToImage,
            contentDescription ="News Cover Image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(10.sdp))
        )

        Spacer(modifier = Modifier.height(10.sdp))

        Text(
            text = article?.source?.name?:"",
            style = MaterialTheme.typography.titleMedium,
            )

        Spacer(modifier = Modifier.height(5.sdp))

        Text(
            text = article?.title?:"",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(5.sdp))

        Text(
            text = article?.publishedAt?.toDuration() ?:"",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.End))


    }
}


fun String.toDuration():String{


    val timeNow = Date.from(Instant.now().atZone(ZoneId.of("UTC")).toInstant())

    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    val publishDateAsDate = formatter.parse(this)

    val timeDifference = timeNow.time - publishDateAsDate.time

    val days = (timeDifference / (1000*60*60*24)).toInt()

    val hours = (((timeDifference - (1000*60*60*24*days)) / (1000*60*60))).toInt()

    val minutes = ((timeDifference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60)).toInt()

    if (days<1 && hours < 1){

        if (minutes ==1) return "1 minute ago"
        else return "$minutes minutes ago"

    } else if (hours>=1 && days<1){

        if (hours ==1) return "1 hour ago"
        else return "$hours hours ago"

    }else {

        if (days ==1) return "1 day ago"
        else return "$days days ago"
    }

}

