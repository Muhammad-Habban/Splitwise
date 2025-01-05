package com.example.splitwise.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.splitwise.Entities.GroupUserCrossRef

@Dao
interface GroupUserCrossRefDao {
    @Insert
    suspend fun insertGroupUserCrossRef(crossRef: GroupUserCrossRef)

    @Query("SELECT userId FROM groupusercrossref WHERE groupId = :groupId")
    suspend fun getUsersInGroup(groupId: Int): List<Int> // Returns the user IDs in the group

    @Query("SELECT groupId FROM groupusercrossref WHERE userId = :userId")
    suspend fun getGroupsForUser(userId: Int): List<Int> // Returns the group IDs for a user
}
