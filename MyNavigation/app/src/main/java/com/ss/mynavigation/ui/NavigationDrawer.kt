package com.ss.mynavigation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.Green)
        .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }

}

@Composable
fun DrawerBody(
    listItems: List<BottomBarScreen>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (BottomBarScreen) -> Unit,
) {
    LazyColumn(modifier) {
        items(listItems) { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }
                .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = null)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
fun PreviewNavDrawer() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)) {
        DrawerHeader()
        DrawerBody(listItems = listOf<BottomBarScreen>(
            BottomBarScreen.Home,
            BottomBarScreen.Profile,
            BottomBarScreen.Setting
        ),
            onItemClick = {}
        )
    }

}
