package com.example.splitwise.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.splitwise.Entities.UserExpense

data class UserOweDetails(
    val debtorUserId: Int,
    val totalOwed: Double
)

@Dao
interface UserExpenseDao {
    @Insert
    suspend fun insertUserExpense(userExpense: UserExpense)

    @Query("""
    SELECT debtorUserId, SUM(amount) as totalOwed
    FROM user_expenses
    WHERE groupId = :groupId
    GROUP BY debtorUserId
    HAVING totalOwed > 0
""")
    suspend fun getUsersWhoOweInGroup(groupId: Int): List<UserOweDetails>


    @Query("SELECT * FROM user_expenses WHERE groupId = :groupId")
    suspend fun getExpensesByGroupId(groupId: Int): List<UserExpense>

    @Query("SELECT * FROM user_expenses WHERE payerUserId = :payerUserId AND debtorUserId = :debtorUserId")
    suspend fun getExpensesBetweenUsers(payerUserId: Int, debtorUserId: Int): List<UserExpense>

    @Query("SELECT SUM(amount) FROM user_expenses WHERE debtorUserId = :userId AND groupId = :groupId")
    suspend fun getTotalDebtForUserInGroup(userId: Int, groupId: Int): Double?
}
