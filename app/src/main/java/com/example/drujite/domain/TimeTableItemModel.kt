package com.example.drujite.domain

data class TimeTableItemModel(val item: String, val time: String?, val isTitle: Boolean)

fun getTimeTableItemsTest(): List<TimeTableItemModel> {
    return listOf(
        TimeTableItemModel(
            "Подъем", "8:45", true
        ),
        TimeTableItemModel(
            "Завтрак", "", true
        ),
        TimeTableItemModel(
            "Старший клан 1", "9:00", false
        ),
        TimeTableItemModel(
            "Старший клан 2", "9:15", false
        ),
        TimeTableItemModel(
            "Младший клан", "9:30", false
        ),
        TimeTableItemModel(
            "Уроки", "", true
        ),
        TimeTableItemModel(
            "Урок 1", "10:20-11:00", false
        ),
        TimeTableItemModel(
            "Урок 2", "11:10-11:50", false
        ),
        TimeTableItemModel(
            "Урок 3", "12:00-12:40", false
        ),
        TimeTableItemModel(
            "Урок 4", "12:50-13:30", false
        ),
        TimeTableItemModel(
            "Обед", "", true
        ),
        TimeTableItemModel(
            "Старший клан 1", "13:40", false
        ),
        TimeTableItemModel(
            "Старший клан 2", "13:55", false
        ),
        TimeTableItemModel(
            "Младший клан", "14:10", false
        ),
        TimeTableItemModel(
            "Дневная ролевая игра", "", true
        ),
        TimeTableItemModel(
            "Альянсная игра", "16:00", false
        ),
        TimeTableItemModel(
            "Час персонажа", "18:00", false
        ),
        TimeTableItemModel(
            "Ужин", "", true
        ),
        TimeTableItemModel(
            "Старший клан 1", "19:00", false
        ),
        TimeTableItemModel(
            "Старший клан 2", "19:15", false
        ),
        TimeTableItemModel(
            "Младший клан", "19:30", false
        ),
        TimeTableItemModel(
            "Вечерняя ролевая игра", "20:00", true
        ),
        TimeTableItemModel(
            "Клановый сбор", "21:30", false
        ),
        TimeTableItemModel(
            "Отбой", "22:30", true
        )
    )
}