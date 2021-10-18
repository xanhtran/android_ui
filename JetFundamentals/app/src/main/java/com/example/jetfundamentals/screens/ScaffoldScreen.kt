package com.example.jetfundamentals.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.jetfundamentals.router.BackButtonHandler
import com.example.jetfundamentals.router.JetFundamentalsRouter
import com.example.jetfundamentals.router.Screen
import kotlinx.coroutines.CoroutineScope

@Composable
fun ScaffoldScreen() {
  MyScaffold()

  BackButtonHandler {
    JetFundamentalsRouter.navigateTo(Screen.Navigation)
  }
}

@Composable
fun MyScaffold() {
  //TODO write your code here
}

@Composable
fun MyTopAppBar(scaffoldState: ScaffoldState, scope: CoroutineScope) {
  //TODO write your code here
}

@Composable
fun MyBottomAppBar() {
  //TODO write your code here
}