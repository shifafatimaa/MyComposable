package com.example.mycomposable

import android.annotation.SuppressLint
import android.hardware.lights.Light
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycomposable.ui.theme.MyComposableTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import kotlin.io.encoding.Base64

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // You might need to handle edge-to-edge display using WindowInsets or another approach.
//        setContent {
//            MyComposableTheme {
//                Scaffold(
//                    topBar = {
//                        TopAppBar(
//                            title = {
//                                Text("This is Shifa")
//                            },
//                            navigationIcon = {
//                                IconButton(onClick = { /* TODO: Handle menu click */ }) {
//                                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
//                                }
//                            },
//                            actions = {
//                                IconButton(onClick = { /* TODO: Handle notifications click */ }) {
//                                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
//                                }
//                                IconButton(onClick = { /* TODO: Handle search click */ }) {
//                                    Icon(Icons.Filled.Search, contentDescription = "Search")
//                                }
//                            }
//                        )
//                    },
//                    floatingActionButton = {
//                        FloatingActionButton(onClick = { /* TODO: Handle add click */ }) {
//                            Icon(Icons.Filled.Add, contentDescription = "Add")
//                        }
//                    },
//                    floatingActionButtonPosition = FabPosition.End,
//                    modifier = Modifier.fillMaxSize()
//                ) { innerPadding ->
//                    MyComposableTheme {
//                        Surface(color = MaterialTheme.colorScheme.background) {
//                            ShowSwitch(
//                                modifier = Modifier.padding(innerPadding)
//                            )
//                        }
//                    }
        setContent{
            MyComposableTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MyApp()
                }
            }

        }


    }
}






@Composable
fun MyApp(){
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    if(shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding=false})
    }
    else{
        Greetings()
    }
}
@Composable
fun Greetings(names:List<String> = List(1000){"$it"}){
    Surface(color= MaterialTheme.colorScheme.background, modifier = Modifier.padding(vertical = 14.dp)) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            LazyColumn {
                item{Text("My List")}
                items(names){
                    name ->
                    Greeting(name)
                }
            }
        }
    }
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun Greeting(name: String) {
    var expanded = remember {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(targetValue = if(expanded.value) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 1500
        )
    )
    Surface (color=MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 8.dp,vertical=4.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(
                    text = "Hello, "
                )
                Text(
                    text = name
                )
            }
            OutlinedButton(onClick = {
                expanded.value = !expanded.value
            },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Red
                )) {
                Text(if(expanded.value) "Show Less" else "Show More")
            }
        }
    }
}
@Composable
fun OnboardingScreen(onContinueClicked:() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
        OnboardingScreen(onContinueClicked = {})
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview(){
    MyComposableTheme {
        MyApp()
    }

}


//@Composable
//fun ShowSwitch(modifier: Modifier = Modifier) {
//    Surface(color =MaterialTheme.colorScheme.primary) {
//
//        // State to hold the switch's checked state
//        val isChecked = remember { mutableStateOf(true) }
//
//        // Switch composable
//        Switch(
//            checked = isChecked.value,
//            onCheckedChange = { isChecked.value = it },
//            modifier = modifier
//                .size(50.dp) // Adjust size as needed
//                .padding(16.dp) // Adjust padding as needed
//        )
//    }
//}
