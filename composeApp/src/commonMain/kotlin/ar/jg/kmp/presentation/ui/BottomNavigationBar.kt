package ar.jg.kmp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(icon = Icons.Filled.Home, label = "Home", onClick = { /* TODO */ })
        BottomNavItem(
            icon = Icons.Filled.Notifications,
            label = "Transfer",
            onClick = { /* TODO */ })
        BottomNavItem(
            icon = Icons.Filled.LocationOn,
            label = "Stats",
            onClick = { /* TODO */ })
        BottomNavItem(
            icon = Icons.Filled.Person,
            label = "Profile",
            onClick = { /* TODO */ })
    }
}