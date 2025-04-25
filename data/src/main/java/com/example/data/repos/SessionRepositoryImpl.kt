package com.example.data.repos

import android.util.Log
import com.example.data.requests.AddUsersCharacterToSession
import com.example.data.requests.GetTimetableBySessionAndDate
import com.example.data.requests.IdRequest
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
                SessionModel(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    dates = it.startDate + " – " + it.endDate,
                    imageUrl = it.imageUrl
                )
            }
        } catch (e: Exception) {
            Log.e("server", "getUsersSessions: ${e.message}")
            emptyList()
        }
    }

    override suspend fun addCharacterToSession(sessionId: Int, characterId: Int): Boolean {
        return try {
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

    override suspend fun getTimetable(sessionId: Int, date: String): TimetableModel {
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
//            val response = sessionApi.getTimetable(
//                authorization = "Bearer $token",
//                sessionId = sessionId,
//                date = date
//            )
            Log.d("server", "getTimetable: $response")
            TimetableModel(
                sessionId = sessionId,
                date = date,
                events = response.map {
                    TimeTableEventModel(
                        id = it.id,
                        timetableId = it.timetableId,
                        header = it.name,
                        time = it.time,
                        isOutlined = it.isTitle
                    )
                }
            )
        } catch (e: Exception) {
            Log.e("server", "getTimetable: ${e.message}")
            throw e
        }
    }

    override suspend fun getSessionByCode(code: String): SessionModel? {
        return null
    }

}