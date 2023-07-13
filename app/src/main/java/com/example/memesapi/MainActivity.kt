package com.example.memesapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
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
//                    Greeting("Android")
//                    MainScreen(
//                        applicationContext,
//                        memesList = mainViewModel.memesListResponse,
//                        navController
//                    )
                    App(applicationContext,
                        memesList = mainViewModel.memesListResponse,)
                    mainViewModel.getMemesInstance()
                }
            }
        }
    }
}


@Composable
fun App(applicationContext: Context, memesList: List<memesList>) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            MainScreen(applicationContext,memesList,navController = navController)
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
//        composable("afterlogin/{mobile}"){
//                NavBackStackEntry ->
//            val mobile = NavBackStackEntry.arguments?.getString("mobile")
//            if (mobile != null) {
//                AfterLoginScreen(mobile = mobile, applicationContext = applicationContext,navController)
//            }
//        }

    }
}
@Composable
fun MainScreen(
    applicationContext: Context,
    memesList: List<memesList>,
    navController: NavHostController
)
{
    Log.d("meme","$memesList")
    Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Latest MEMES", fontSize = 32.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        LazyColumn {

            itemsIndexed(items = memesList) {
                    index, item ->
                Card(
                    Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth()
                        .clickable {
//                            navController.navigate("description/${item.lines}/${item.overlays}/${item.source}")
                        }) {
                    if (memesList.isNotEmpty()) {
                        Log.d("meme","$item")
//                        Row(Modifier.padding(10.dp)) {
                            Image(painter = rememberImagePainter(data = "https://api.memegen.link/images/agnes.png",
                                builder = {
                                    scale(coil.size.Scale.FILL)
                            placeholder(R.drawable.placeholder)
                                })
                                , contentDescription = "")
//                            Column() {
//                                Text(text = "Name : ${item.name}",Modifier.padding(10.dp))
//                                Text(text = "Id : ${item.id}",Modifier.padding(10.dp))
//                                Text(text = "Overlays : ${item.overlays}",Modifier.padding(10.dp))
//                                Text(text = "Lines : ${item.lines}",Modifier.padding(10.dp))
//                            }
//                        }

                        Log.d("url","${item.blank}")

//                        var painter = rememberImagePainter(
//                            data = item.meme?.url,
//                            builder = {
//                                scale(Scale.FILL)
//                                placeholder(R.drawable.placeholder)
//                                transformations(CircleCropTransformation())
//                            }
//                        )
                    }
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
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemesApiTheme {
        Greeting("Android")
    }
}