package com.example.drujite.presentation.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.domain.models.TimeTableEventModel
import com.example.drujite.R
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyTitle
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimeTableView(
    viewModel: TimetableViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()

    when (val state = viewState.value) {
        is TimetableState.Main -> {
            MainState(state)
        }

        is TimetableState.Initialization -> {
            LoadingScreen()
            viewModel.obtainEvent(TimetableEvent.LoadData)
        }
    }
}

@Composable
fun MainState(
    state: TimetableState.Main
) {
    val currentDate = state.timeTable.date
    val items = state.timeTable.events

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MyTitle(stringResource(R.string.timetable_title))
            Text(text = currentDate)
        }
        items.forEach {
            TimeTableItem(it)
        }
    }
}


@Composable
fun TimeTableItem(item: TimeTableEventModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.header,
            fontSize = 17.sp,
            fontWeight = if (item.isOutlined) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = item.time?: "",
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