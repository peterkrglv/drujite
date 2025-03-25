package com.example.drujite.presentation.session_news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.domain.models.NewsModel
import com.example.drujite.R
import com.example.drujite.presentation.icons.ArrowIcon
import com.example.drujite.presentation.my_composables.LoadingScreen
import com.example.drujite.presentation.my_composables.MyCard
import com.example.drujite.presentation.my_composables.ShortenedText
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsView(
    viewModel: NewsViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsState()

    when (val state = viewState.value) {
        is NewsState.Main -> {
            MainState(
                state = state,
                onQueryChanged = { query -> viewModel.obtainEvent(NewsEvent.QueryChanged(query)) },
                onSearchClicked = { query -> viewModel.obtainEvent(NewsEvent.Search(query)) }
            )
        }

        is NewsState.Loading -> {
            LoadingScreen()
        }

        NewsState.Initialization -> {
            LoadingScreen()
            viewModel.obtainEvent(NewsEvent.LoadData)
        }
    }

}

@Composable
fun MainState(
    state: NewsState.Main,
    onQueryChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit
) {
    val news = state.displayedNews
    val query = state.query

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        MySearchBar(
            query = query,
            onQueryChanged = onQueryChanged,
            onSearchClicked = onSearchClicked
        )
        LazyColumn {
            items(news) { newsItem ->
                MyCard(
                    modifier = Modifier.padding(vertical = 4.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    NewsItem(item = newsItem)
                }
            }
        }
    }
}

@Composable
fun MySearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        value = query,
        onValueChange = onQueryChanged,
        shape = RoundedCornerShape(12.dp),
        trailingIcon = {
            MyCard(
                modifier = Modifier.clickable {
                    onSearchClicked(query)
                },
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentPadding = PaddingValues(8.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .aspectRatio(1f),
                    imageVector = ArrowIcon,
                    contentDescription = "Search",
                    tint = Color(0xFFC8C8C8)
                )
            }
        }
    )
}


@Composable
fun NewsItem(item: NewsModel) {
    val imageUrl =
        "https://fastly.picsum.photos/id/740/300/200.jpg?hmac=02VXkAz54aSHF2uKi1JSmOkJFO7u8_qyEL3KT0oAfQ4"
    Column(

    ) {
        Text(
            text = item.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        ShortenedText(
            text = item.content,
            maxLines = 3
        )
        Image(
            painter = painterResource(id = R.drawable.news_image),
            //painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun NewsViewPreview() {

    AppTheme {
        MainState(
            state = NewsState.Main(
                displayedNews = listOf(
                    NewsModel(
                        id = 1,
                        title = "Title",
                        content = "Content",
                        date = "Date",
                        time = "Time",
                        imageUrl = "url"
                    )
                ),
                query = ""
            ),
            onQueryChanged = {},
            onSearchClicked = {}
        )
    }
}