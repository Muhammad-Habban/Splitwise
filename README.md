# Splitwise Clone - Android Application

## Project Overview
This project is a clone of the **Splitwise** app, designed as an Android application using **Kotlin** and **Android Studio**. The aim is to replicate the key screens and user flow of Splitwise, providing a basic UI for users to sign up, log in, and access the main screen. Currently, the project focuses on the UI design, with future development planned for navigation, user authentication, and database functionality for managing user data and expenses.

The app currently includes three screens:
1. **Main Screen**: The landing page where users can sign up, log in, or sign in with Google.
2. **Sign-Up Screen**: A simple form for entering the user's full name.
3. **Login Screen**: A login form that captures a user's password.

## Setup Instructions
To get started with this project, follow these steps:
1. **Clone the repository**:
   - `git clone https://github.com/Muhammad-Habban/Splitwise.git`
2. **Open the project in Android Studio**:
   - Launch **Android Studio** and open the project folder.

3. **Install dependencies**:
   - Android Studio will prompt you to sync the project with Gradle. Allow it to sync.

4. **Run the app**:
   - Connect an Android device or use the emulator.
   - Click the **Run** button in Android Studio or press `Shift + F10` to build and launch the app.

## Screens Designed and Their Purpose

### 1. Main Screen
   - **Purpose**: The main entry point of the app. Users are presented with three main options:
     - **Sign Up**: Directs the user to the sign-up page.
     - **Log In**: Navigates to the login page.
     - **Sign in with Google**: This option will allow Google authentication in future implementations.
   - **UI Elements**: Includes the Splitwise logo, a "Sign Up" button, a "Log In" button, and a "Sign in with Google" button. There are also links to **Terms**, **Privacy Policy**, and **Contact us**.

### 2. Sign-Up Screen
   - **Purpose**: Allows users to input their full name as the first step in the sign-up process. In the future, this will be connected to a backend system to store user information.
   - **UI Elements**: 
     - An input field for the user's full name.
     - Two buttons:
       - **Back**: Returns the user to the main screen.
       - **Done**: Submits the name and proceeds (future functionality will be added).

### 3. Login Screen
   - **Purpose**: Provides a simple login form for returning users to enter their credentials. Currently, this screen only captures the password.
   - **UI Elements**: 
     - An input field for the password.
     - Two buttons:
       - **Back**: Returns the user to the main screen.
       - **Done**: Processes the login (future functionality will handle authentication).

## Technical Challenges and Future Plans

### Technical Challenges

1. **UI Styling**: Ensuring that the app design is responsive across various screen sizes posed a challenge. Using **ConstraintLayout** was essential for maintaining a flexible and responsive design.
2. **Touch Target Size**: Meeting the minimum touch target size (48dp) for buttons and input fields was a challenge. Android Studio flags elements that are too small, which required adjustments to padding and sizing.
3. **Custom Styling**: Applying custom styling for specific UI components, such as buttons with only a bottom border and specific color schemes, was handled through XML drawables, which added complexity.

### Future Plans

1. **Navigation**: Currently, the screens are not connected. In future updates, I plan to implement proper **navigation** between screens using **Navigation Components** or simple `Intent` transitions.
2. **User Authentication**: Next, I plan to implement actual **user authentication**, either with email/password or through Google Sign-In. This will enable users to create accounts and log in securely.
3. **Database Integration**: To store user data and expenses, I plan to integrate a **Room database** for local storage, or **Firebase** for cloud-based data storage.
4. **Expense Management**: The ultimate goal is to develop a fully functional app where users can track shared expenses, add debts, and settle them, mimicking the core functionality of Splitwise.

## Conclusion
This project is still in progress, with the UI for three key screens completed. The next phases will involve adding backend logic, navigation, and user authentication. Contributions and suggestions are welcome as the project develops further.
