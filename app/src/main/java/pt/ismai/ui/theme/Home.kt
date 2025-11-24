package pt.ismai.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import pt.ismai.SmartTimer

@Composable
fun Home () {
    var initialHours = rememberSaveable { mutableStateOf(0) }
    var initialMinutes = rememberSaveable { mutableStateOf(0) }
    var initialSeconds = rememberSaveable { mutableStateOf(0) }
    SmartTimer( "Timer",initialHours, initialMinutes, initialSeconds)
}