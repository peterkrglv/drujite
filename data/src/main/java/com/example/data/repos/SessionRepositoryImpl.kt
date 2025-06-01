package com.example.data.repos

import android.util.Log
import com.example.data.baseUrl
import com.example.data.requests.AddSessionByQRRequest
import com.example.data.requests.AddUsersCharacterToSession
import com.example.data.requests.GetTimetableBySessionAndDate
import com.example.data.requests.IdRequest
import com.example.data.responces.EventResponse
import com.example.data.responces.SessionResponse
import com.example.domain.models.SessionModel
import com.example.domain.models.TimeTableEventModel
import com.example.domain.models.TimetableModel
import com.example.domain.repos.SessionRepository

class SessionRepositoryImpl(
    private val sessionApi: com.example.data.api.SessionApi,
    private val sharedPrefs: com.example.domain.repos.SharedPrefsRepository
) : SessionRepository {
    override suspend fun getSessionById(id: Int): SessionModel? {
        return try {
            val userToken = sharedPrefs.getUserToken() ?: return null
            val response = sessionApi.getSession(
                request = IdRequest(id),
                token = "Bearer $userToken"
            )
            Log.d("server", "getSessionById: $response")
            SessionModel(
                id = response.id,
                name = response.name,
                description = response.description,
                dates = response.startDate + " – " + response.endDate,
                imageUrl = response.imageUrl
            )
        } catch (e: Exception) {
            Log.e("server", "getSessionById: ${e.message}")
            null
        }
    }

    override suspend fun getUsersSessions(): List<SessionModel> {
        return try {
            val token = sharedPrefs.getUserToken() ?: return emptyList()
            val response = sessionApi.getAllSessions("Bearer $token")
            Log.d("server", "getUsersSessions: $response")
            response.map {
                it.toModel()
            }
        } catch (e: Exception) {
            Log.e("server", "getUsersSessions: ${e.message}")
            emptyList()
        }
    }

    override suspend fun addCharacterToSession(sessionId: Int, characterId: Int): Boolean {
        return try {
            Log.d("server", "Try to add character: $sessionId $characterId")
            val token = sharedPrefs.getUserToken() ?: return false
            sessionApi.add(
                request = AddUsersCharacterToSession(sessionId, characterId),
                token = "Bearer $token"
            )
            Log.d("server", "addCharacterToSession: $sessionId $characterId")
            true
        } catch (e: Exception) {
            Log.e("server", "addCharacterToSession: ${e.message}")
            false
        }
    }

    override suspend fun getTimetable(sessionId: Int, date: String): TimetableModel? {
        return try {
            val token = sharedPrefs.getUserToken() ?: throw Exception("Token not found")
            val request = GetTimetableBySessionAndDate(
                sessionId = sessionId,
                date = date
            )
            Log.d("server", "getTimetable request: $request")
            val response = sessionApi.getTimetable(
                request = GetTimetableBySessionAndDate(sessionId, date),
                authorization = "Bearer $token"
            )
            Log.d("server", "getTimetable: $response")
            TimetableModel(
                sessionId = sessionId,
                date = date,
                events = response.map {
                    it.toModel()
                }
            )
        } catch (e: Exception) {
            Log.e("server", "getTimetable: ${e.message}")
            return TimetableModel(
                sessionId = sessionId,
                date = date,
                events = emptyList()
            )
        }
    }

    override suspend fun getSessionByCode(code: String): SessionModel? {
        return try {
            val token = sharedPrefs.getUserToken() ?: return null
            sessionApi.addSessionByQr(
                request = com.example.data.requests.AddSessionByQRRequest(code),
                token = "Bearer $token"
            )
            Log.d("server", "getSessionByCode: $code")
            val session = sessionApi.addSessionByQr(
                request = AddSessionByQRRequest(code),
                token = "Bearer $token"
            )
            return session.toModel()
        } catch (e: Exception) {
            Log.e("server", "getSessionByCode: ${e.message}")
            null
        }
    }

}

private fun SessionResponse.toModel() =
    SessionModel(
        id = id,
        name = name,
        description = description,
        dates = "${convertDate(startDate)} – ${convertDate(endDate)}",
        imageUrl = if (imageUrl == "null" || imageUrl == null) null else baseUrl + imageUrl,
    )

private fun EventResponse.toModel() =
    TimeTableEventModel(
        id = id,
        timetableId = timetableId,
        header = name,
        time = if (time == "" || time == "null") null else time,
        isOutlined = isTitle
    )


fun convertDate(input: String): String {
    val regex = Regex("""(\d{4})-(\d{2})-(\d{2})T""")
    val matchResult = regex.find(input)
    return if (matchResult != null) {
        val (year, month, day) = matchResult.destructured
        "$day.$month"
    } else {
        "Invalid date"
    }
}