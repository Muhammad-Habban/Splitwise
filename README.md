# Splitwise Clone - Android Application

## Project Overview

This project is a clone of the Splitwise app, designed as an Android application using Kotlin and Android Studio. The aim is to replicate the key screens and user flow of Splitwise, providing a UI for users to sign up, log in, and access the main screen, along with additional features for managing expenses and balances. Currently, the project focuses on the UI design, with plans for implementing navigation, user authentication, and database functionality to manage user data and expenses.

The app now includes the following screens:

- **Main Screen**: The landing page where users can sign up, log in, or sign in with Google.
- **Sign-Up Screen**: A form to enter the user's full name.
- **Login Screen**: A login form that captures a user’s password.
- **Groups Screen**: Displays user groups and shared expenses.
- **Friends Screen**: Shows a list of the user’s friends within the app.
- **Activity Screen**: Displays the user’s recent activities and transactions.
- **Account Screen**: Provides user account details and settings options.
- **Totals Screen**: Displays the total expenses and balances across all groups.
- **Settle Up Screen**: Allows users to settle debts with friends or within groups.
- **Balances Screen**: Shows detailed balances for each friend or group.
- **Whiteboard Screen**: Provides an open text area for note-taking.
- **Add New Group Screen**: Allows users to create a new group for shared expenses.
- **Add Expense Screen**: Enables adding a new expense within a group or with a friend.
- **Group Detail Screen**: Provides details for a specific group, including members and transactions.


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

## Screens Designed and Their Purpose

### 1. Main Screen

- **Purpose**: The main entry point of the app, presenting users with options to sign up, log in, or sign in with Google.
- **UI Elements**: Includes buttons for Sign Up, Log In, and Google Sign-In, as well as links to Terms, Privacy Policy, and Contact Us.

### 2. Sign-Up Screen

- **Purpose**: Allows users to input their full name as the first step in the sign-up process.
- **UI Elements**: Input field for the full name, Back button, and Done button.

### 3. Login Screen

- **Purpose**: Provides a simple login form for returning users.
- **UI Elements**: Password input field, Back button, and Done button.

### 4. Groups Screen

- **Purpose**: Displays the user's groups and associated expenses.
- **UI Elements**: Group cards with group names, debt summaries, and member details.

### 5. Friends Screen

- **Purpose**: Displays a list of friends, allowing users to manage connections and track shared expenses.
- **UI Elements**: Friends list with connections and balance details.

### 6. Activity Screen

- **Purpose**: Shows recent activity and transaction history between the user and other members.
- **UI Elements**: List of recent transactions and settlements.

### 7. Account Screen

- **Purpose**: Provides user account details and settings.
- **UI Elements**: Profile picture, name, email, preferences for notifications, email settings, and a Log Out button.

### 8. Totals Screen (New)

- **Purpose**: Displays total expenses and balances for all groups.
- **UI Elements**: Summary of expenses and contributions across all groups.

### 9. Settle Up Screen (New)

- **Purpose**: Enables users to settle outstanding debts within groups or with friends.
- **UI Elements**: Interface to choose settlement options, select friends, and settle debts.

### 10. Balances Screen (New)

- **Purpose**: Shows detailed balances owed or owing for each friend or group.
- **UI Elements**: List of individual balances for easy tracking.

### 11. Whiteboard Screen (New)

- **Purpose**: Provides an open text area for note-taking, mimicking a whiteboard experience.
- **UI Elements**: A full-screen `EditText` for jotting down notes or reminders.

### 12. Add New Group Screen (New)

- **Purpose**: Allows users to create a new group for tracking shared expenses.
- **UI Elements**: Input fields for group name, members, and creation options.

### 13. Add Expense Screen (New)

- **Purpose**: Allows users to add new expenses within a group or with friends.
- **UI Elements**: Expense details form with options for amount, description, and sharing method.

### 14. Group Detail Screen (New)

- **Purpose**: Provides a detailed view of a group, including members, expenses, and balances.
- **UI Elements**: Group information, members list, and transaction history.

## Technical Challenges and Future Plans

### Technical Challenges

- **UI Styling**: Ensuring responsive design across screen sizes using `ConstraintLayout`.
- **Touch Target Size**: Meeting minimum touch target size requirements, which required adjustments to padding and sizing.
- **Custom Styling**: Applying custom styling, including buttons with borders and specific color schemes.
- **Fragment and Activity Management**: Managing transitions between fragments and activities, especially in complex navigation flows with subpages.
- **Data Consistency**: Ensuring that data flows correctly between fragments and activities, maintaining state across subpages.

### Future Plans

- **User Authentication**: Implementing secure user authentication, either with email/password or Google Sign-In.
- **Database Integration**: Storing user data and expenses locally with Room or Firebase for cloud-based storage.
- **Expense Management**: Developing functionality to track and manage shared expenses, settlements, and balances with friends and groups.
- **Enhanced Navigation**: Further improve navigation for complex flows and deep linking within the app.
- **Settings and Preferences**: Adding user preferences such as dark mode, language options, and additional notification settings.
- **Testing and Bug Fixes**: Conducting unit testing and UI testing to ensure app stability and usability across devices.

## Conclusion

This project now includes 14 key screens, providing a comprehensive UI for managing shared expenses in a format similar to Splitwise. The next steps involve adding backend functionality, authentication, and data management. Contributions and suggestions are welcome as the project progresses.
