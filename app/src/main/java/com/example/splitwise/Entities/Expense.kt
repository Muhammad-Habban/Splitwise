package com.example.splitwise.Entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val expenseId: Int = 0,
    val groupId: Int, // Foreign key to the group
    val amount: Double,
    val description: String,
    val paidByUserId: Int // Foreign key to the user who paid
)
