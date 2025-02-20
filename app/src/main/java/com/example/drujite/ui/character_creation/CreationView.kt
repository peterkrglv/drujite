import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.drujite.ui.*

@Composable
fun CreationView() {
    MainState()
}

@Composable
fun MainState() {
    val name = remember { mutableStateOf("") }
    val clan = remember { mutableStateOf("") }
    val clans = listOf("Клан 1", "Клан 2", "Клан 3", "Клан 4", "Клан 5", "Клан 6", "Клан 7", "Клан 8", "Клан 9", "Клан 10", "Клан 11")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            MyTitle(text = "Создай персонажа")
            MyTitle2(text = "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне.")
            Spacer(modifier = Modifier.height(32.dp))
            MyTextField(
                value = name.value,
                label = "Имя и фамилия персонажа",
                isError = false,
            ) {
                name.value = it
            }
            DropdownTextField(
                label = "Выбери клан",
                options = clans,
                selected = ""
            ) {
                clan.value = it
            }
            MyButton(text = "Дальше", onClick = {})
        }
        TextButtonNavigation(
            text = "Хочешь перенести персонажа?",
            buttonText = "Перенос",
            onClick = {}
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun CreationPreview() {
    AppTheme {
        CreationView()
    }
}