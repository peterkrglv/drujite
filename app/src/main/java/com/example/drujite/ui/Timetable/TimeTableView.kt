package com.example.drujite.ui.Timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.drujite.domain.TimeTableItemModel
import com.example.drujite.domain.getTimeTableItemsTest
import com.example.drujite.ui.MyTitle

@Composable
fun TimeTableView() {
    MainView()
}

@Composable
fun MainView() {
    val currentDate = "27 марта"
    val items = getTimeTableItemsTest()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MyTitle("Расписание дня")
            Text(text = currentDate)
        }
        items.forEach {
            TimeTableItem(it)
        }
    }

}


@Composable
fun TimeTableItem(item: TimeTableItemModel) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.item,
            fontSize = 17.sp,
            fontWeight = if (item.isTitle) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = item.time ?: "",
            fontSize = 17.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TimeTableViewPreview() {
    AppTheme {
        TimeTableView()
    }
}