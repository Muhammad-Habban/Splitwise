package com.example.splitwise.Entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_expenses")
data class UserExpense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val payerUserId: Int, // User who paid
    val debtorUserId: Int, // User who owes
    val groupId: Int, // Group the expense belongs to
    val amount: Double
)
