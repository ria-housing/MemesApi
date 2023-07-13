package com.example.memesapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.memesapi.ui.theme.MemesApiTheme

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemesApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(memesList = mainViewModel.memesListResponse,)
                    mainViewModel.getMemesInstance()
                }
            }
        }
    }
}
@Composable
fun App(memesList: List<memesList>) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            MainScreen(memesList)
        }
        composable("description/{lines}/{overlays}/{source}"){
            NavBackStackEntry ->
            val lines = NavBackStackEntry.arguments?.getInt("lines")
            val overlays = NavBackStackEntry.arguments?.getInt("overlays")
            val source = NavBackStackEntry.arguments?.getString("source")
            if (lines != null && overlays != null && source != null) {
                    description(lines,overlays,source)
            }
        }

    }
}
@Composable
fun MainScreen( memesList: List<memesList>) {
    //Log.d("meme","$memesList")
    Column(
        Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Latest MEMES",
            fontSize = 32.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        LazyColumn {
            itemsIndexed(items = memesList) { index, item ->
                Card(
                    Modifier
                        .padding(all = 10.dp)
                        .fillMaxSize()
                        .clickable {
                        }) {
                    if (memesList.isNotEmpty()) {
                        Column(Modifier.padding(10.dp)) {
                            MovieItem(st = "https://api.memegen.link/images/agnes.png")
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Great")
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun MovieItem(st:String)
{


    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxSize()
            .selectable(true, true, null,
                onClick = {
                })
            .height(180.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Surface(color = Color.White) {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()

            )
            {
                Image(
                    painter = rememberImagePainter(
                        data = st,
                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.placeholder)
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "movie.bio",
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                        .background(Color.Yellow)
                        .padding(20.dp)
                        .selectable(true, true, null,
                            onClick = {

                            })
                ) {

                    Text(
                        text = "c",
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }

}


@Composable
fun description(lines: Int, overlays: Int, source: String) {
    Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){
        Card() {
            Text(text = "lines : $lines")
            Text(text = "overlay : $overlays")
            Text(text = "source : $source")
        }

    }
}

