package com.example.newsapp.ArticleDetails

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.domain.model.ArticlesItem
import com.example.newsapp.ui.theme.blue
import com.example.newsapp.utils.sdp
import com.example.newsapp.utils.ssp


@Composable
fun ArticlesDetailsContent(
    articlesItem: ArticlesItem
){

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .verticalScroll(rememberScrollState())
    ){

        AsyncImage(
            model = articlesItem.urlToImage,
            contentDescription = "News Cover Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(1f)
        )

        Spacer(modifier = Modifier.height(10.sdp))

        Text(
            text = articlesItem.source?.name?:"",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 16.sdp, end = 16.sdp)
        )

        Text(
            text = articlesItem.title?:"",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.sdp, end = 16.sdp)
        )

        Spacer(modifier = Modifier.height(10.sdp))

        Row (
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 16.sdp, end = 16.sdp)
        ){

            Text(
                text = articlesItem.author?:"",
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.width(10.sdp))

            Text(
                text = articlesItem.publishedAt?.formatDate() ?:"",
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge
            )


        }

        Spacer(modifier = Modifier.height(10.sdp))

        Text(
            text = articlesItem.content?.removeSquareBrackets()?:"",
            fontSize = 20.ssp,
            modifier = Modifier.padding(start = 16.sdp, end = 16.sdp)
            )

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {

                val  browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articlesItem.url));
                context.startActivity(browserIntent)

            }

        ) {

            Text(
                text = "Continue reading",
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge,

            )

            Icon(Icons.Default.PlayArrow, contentDescription ="continue reading article" )
        }

        Spacer(modifier = Modifier.weight(1f))


        Button(
            onClick = {

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, articlesItem.title + "\n" + "\n" + articlesItem.url)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            },
            shape = RoundedCornerShape(19.sdp),
            colors = ButtonDefaults.buttonColors(blue),
            modifier = Modifier
                .padding(start = 16.sdp, end = 16.sdp)
                .fillMaxWidth(1f)
                .height(65.sdp)
        ) {

            Text(
                text = "Share",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.ssp,
                color = Color.White
            )

        }

        Spacer(modifier = Modifier.height(16.sdp))

    }


}

fun String.removeSquareBrackets():String{

   return this.substringBefore('[')

}

fun String.formatDate():String{

    val inputDateformatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    val inputDateFormated = inputDateformatter.parse(this)

    val formatter = SimpleDateFormat("EEEE, dd MMM yyyy")

    return formatter.format(inputDateFormated)

}