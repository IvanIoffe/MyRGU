package com.application.myrgu.schedule.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.myrgu.schedule.data.source.local.model.ScheduleLocal
import com.application.myrgu.schedule.data.source.local.model.ScheduleVersionLocal

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule WHERE schedule_key = :scheduleKey")
    suspend fun getSchedule(scheduleKey: String): ScheduleLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSchedule(schedule: ScheduleLocal)

    @Query("DELETE FROM schedule")
    suspend fun deleteAllSchedule()

    @Query("SELECT schedule_version FROM schedule WHERE schedule_key = :scheduleKey")
    suspend fun getScheduleVersion(scheduleKey: String): ScheduleVersionLocal
}