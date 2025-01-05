package com.example.splitwise.DataAccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.splitwise.DAO.UserDao
import com.example.splitwise.DAO.GroupDao
import com.example.splitwise.DAO.ExpenseDao
import com.example.splitwise.Entities.User
import com.example.splitwise.Entities.Group
import com.example.splitwise.Entities.GroupUserCrossRef
import com.example.splitwise.DAO.UserExpenseDao
import com.example.splitwise.DAO.GroupUserCrossRefDao
import com.example.splitwise.Entities.Expense
import com.example.splitwise.Entities.UserExpense

@Database(
    entities = [User::class, Group::class, GroupUserCrossRef::class, Expense::class, UserExpense::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun groupDao(): GroupDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun groupUserCrossRefDao(): GroupUserCrossRefDao
    abstract fun userExpenseDao(): UserExpenseDao

}
