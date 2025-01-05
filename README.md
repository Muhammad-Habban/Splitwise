# Splitwise Clone - Android Application

## Project Overview

This project is a comprehensive clone of the Splitwise app, designed as an Android application using Kotlin and Android Studio. The application enables users to manage shared expenses, settle balances, and track group transactions effectively. It replicates the essential features of Splitwise while adding custom functionality to enhance the user experience.

The app now includes the following features:

- **Group Management**: Create, view, and manage groups, including adding users to groups dynamically.
- **Expense Tracking**: Add and split expenses among group members with detailed transaction history.
- **Debt Management**: View balances and settle up with group members.
- **User-Friendly Navigation**: Intuitive screens for managing groups, friends, and expenses.

---

## New Features Added

- **Add Users to Group**: Dynamically add friends to an existing group, filtering out current group members and the logged-in user.
- **Expense Splitting**: Automatically split expenses equally among all group members.
- **Settle Up**: View detailed information about who owes whom in a group and settle debts.
- **Enhanced Group Detail Screen**: Display group information, members, and a detailed list of expenses with payer information.
- **Persistent Data Storage**: Use Room Database for local storage and Firebase Authentication for user management.

---

## Setup Instructions

To get started with this project, follow these steps:

1. **Clone the repository**:

    ```bash
    git clone https://github.com/Muhammad-Habban/Splitwise.git
    ```

2. **Open the project in Android Studio**:

    - Launch Android Studio and open the project folder.

3. **Install dependencies**:

    - Android Studio will prompt you to sync the project with Gradle. Allow it to sync.

4. **Run the app**:

    - Connect an Android device or use the emulator.
    - Click the Run button in Android Studio or press `Shift + F10` to build and launch the app.

---

## Screens and Features

### 1. **Group Management**

- **Group Creation**: Create new groups with a custom name.
- **Group Details**: View detailed group information, including expenses, balances, and group members.
- **Add Users**: Dynamically add new users to a group, excluding the current user and existing group members.

### 2. **Expense Tracking**

- **Add Expense**: Add new expenses to a group, specifying the payer and splitting the cost equally among all group members.
- **View Expenses**: See detailed information about each expense in the group, including the description, amount, and payer.

### 3. **Debt Management**

- **Settle Up**: View and manage all debts within a group, showing who owes whom and how much.
- **Balances**: View detailed balances for all group members.

---

## Technical Highlights

### 1. **Database Integration**

- **Room Database**: Used for storing users, groups, expenses, and group-user relationships.
- **Data Entities**:
  - `User`: Stores user information.
  - `Group`: Stores group details.
  - `Expense`: Tracks expenses with descriptions, amounts, and payer details.
  - `UserExpense`: Tracks how much each user owes for each expense.

### 2. **User Authentication**

- **Firebase Authentication**: Handles secure user authentication.

### 3. **Dynamic UI Components**

- **Group Cards**: Dynamically display all groups for the current user.
- **Expense Cards**: Dynamically display all expenses within a group.
- **Settle Up Cards**: Show detailed debt information for group members.

### 4. **Error Handling**

- Comprehensive error handling for database operations and user interactions.

---

## Technical Challenges and Solutions

### Challenges

1. **Dynamic Filtering**: Filtering users for the "Add Users" feature to exclude the current user and existing group members.
2. **Expense Splitting**: Calculating and storing each user's share of an expense accurately.
3. **Efficient Data Queries**: Fetching group-specific data for expenses and user debts efficiently.

### Solutions

- Implemented custom queries in Room Database to handle dynamic filtering and data aggregation.
- Used coroutine-based architecture for efficient data fetching and UI updates.
- Leveraged Kotlin's data classes for better structure and maintainability.

---

## Future Plans

- **Enhanced UI/UX**: Add animations and visual improvements for smoother navigation.
- **Cloud Storage**: Integrate Firebase Firestore for syncing data across devices.
- **Advanced Reports**: Provide detailed reports of expenses and balances.
- **Push Notifications**: Notify users about new expenses or settlements in real-time.

---

## Conclusion

This project now includes comprehensive functionality for managing shared expenses, replicating and enhancing the Splitwise experience. Contributions and suggestions are welcome to make the project even better.

---
