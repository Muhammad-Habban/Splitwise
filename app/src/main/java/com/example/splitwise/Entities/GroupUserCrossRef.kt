package com.example.splitwise.Entities
import androidx.room.Entity

@Entity(primaryKeys = ["userId", "groupId"])
data class GroupUserCrossRef(
    val userId: Int,
    val groupId: Int
)
