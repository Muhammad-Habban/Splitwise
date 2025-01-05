package com.example.splitwise.DAO
import com.example.splitwise.Entities.Group
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GroupDao {
    @Insert
    suspend fun insertGroup(group: Group): Long

    @Query("SELECT * FROM groups WHERE groupId = :groupId")
    suspend fun getGroupById(groupId: Int): Group

    @Query("SELECT * FROM groups")
    suspend fun getAllGroups(): List<Group>
}
