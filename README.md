# PetAdoptionExplorer

# 🐾 PetAdoptionExplorer

PetAdoptionExplorer is an Android application developed using **Kotlin and Jetpack Compose** that allows users to explore dog breeds, learn about their characteristics, discover breeds based on their preferences, save favourite breeds, and initiate adoption requests.

The application provides a user-friendly interface for browsing dog breeds using information retrieved from **The Dog API**. Users can search for breeds, filter dogs based on characteristics, view detailed information, bookmark their favourite breeds, and submit adoption enquiries.

---

## 📌 Project Overview

PetAdoptionExplorer aims to make exploring dog breeds easier and more interactive by providing information about different breeds in a single mobile application.

The app combines API-based data retrieval with local storage to provide features such as breed discovery, favourites, adoption enquiries, and personalized settings.

### Objectives

- Provide an easy-to-use interface for exploring dog breeds.
- Retrieve breed information dynamically using an API.
- Allow users to search and filter breeds.
- Display detailed information about individual breeds.
- Allow users to save and manage favourite breeds.
- Provide an adoption enquiry workflow.
- Maintain user preferences such as dark mode across app restarts.

---

## ✨ Features

### 🐕 Breed Exploration
- Displays a collection of dog breeds.
- Fetches breed information from The Dog API.
- Displays breed images and relevant information.

### 🔍 Search and Filters
- Search dog breeds by name.
- Filter breeds by breed group.
- Filter breeds by origin.
- Clear filters to return to the default listing.

### 📖 Breed Details
Users can view detailed information about a selected breed, including:
- Breed name
- Breed group
- Temperament
- Life span
- Origin
- Image

### ❤️ Favourites
- Add breeds to favourites.
- Remove breeds from favourites.
- View saved breeds in a separate screen.
- Favourite data persists locally using Room Database.

### 🏡 Adoption Requests
- Submit an adoption enquiry for a selected breed.
- Enter applicant details.
- Receive a confirmation screen after submission.
- Store adoption request details locally.

### 🌙 Dark Theme
- Toggle between light and dark themes.
- Save the theme preference locally.
- Restore the selected theme when reopening the app.

### 🌐 External Intents
- Open external adoption resources in a web browser.
- Launch an email application with a pre-filled adoption enquiry.

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Main programming language |
| Jetpack Compose | UI development |
| Android Studio | Development environment |
| Retrofit | API communication |
| The Dog API | Dog breed data |
| Gson | JSON conversion |
| Room Database | Local persistence |
| SharedPreferences | Theme preference persistence |
| Kotlin Coroutines | Asynchronous operations |
| Navigation Compose | Screen navigation |
| Glide | Loading and displaying dog images |

---

## 🏗️ Architecture & Project Structure

The project follows a modular structure separating UI, data handling, API communication, navigation, preferences, and utility components.

```text
PetAdoptionExplorer/
│
├── data/
│   ├── remote/
│   │   ├── DogApiService.kt
│   │   └── RetrofitInstance.kt
│   │
│   └── local/
│       ├── AppDatabase.kt
│       ├── DogEntity.kt
│       ├── DogDao.kt
│       ├── DogRepository.kt
│       ├── AdoptionRequestEntity.kt
│       └── AdoptionRequestDao.kt
│
├── model/
│   ├── Dog.kt
│   └── AdoptionRequest.kt
│
├── navigation/
│   └── AppNavigation.kt
│
├── preferences/
│   └── PreferencesManager.kt
│
├── ui/
│   ├── screens/
│   │   ├── DogHomeScreen.kt
│   │   ├── DogDetailsScreen.kt
│   │   ├── FavoritesScreen.kt
│   │   ├── SettingsScreen.kt
│   │   └── AdoptionRequestScreen.kt
│   │
│   ├── components/
│   │   ├── DogCard.kt
│   │   └── DogFilterSection.kt
│   │
│   └── theme/
│
├── utils/
│   └── IntentHelper.kt
│
└── MainActivity.kt
```

---

## 💾 Database

The application uses **Room Database**, which provides an abstraction layer over SQLite for local data storage.

### Local Storage Used

- Favourite dog breeds
- Adoption request information

### Main Database Components

| Component | Responsibility |
|---|---|
| Entity | Defines the structure of database tables |
| DAO | Defines database operations |
| Room Database | Provides database access and manages entities |
| Repository | Acts as an intermediary between UI and data sources |

The application also uses **SharedPreferences** to store user settings such as the selected theme.

---

## 🔌 API Integration

The application uses **The Dog API** to retrieve dog breed information and images.

API website:

https://thedogapi.com/

The application uses Retrofit to perform network requests and Gson to convert JSON responses into Kotlin objects.

API information is loaded dynamically rather than being hardcoded into the application.

---

## 📱 Application Screenshots

> Add screenshots of your application in the `screenshots/` directory and replace the placeholder paths below with the actual image names.

### 1. Home Screen

Displays dog breed cards, search functionality, and filtering options.

![Home Screen](screenshots/home.png)

### 2. Breed Details Screen

Displays detailed information about a selected breed, including its characteristics and adoption option.

![Breed Details](screenshots/breed_details.png)

### 3. Favourites Screen

Displays the breeds saved by the user.

![Favourites](screenshots/favourites.png)

### 4. Adoption Request Confirmation

Displays confirmation after an adoption enquiry has been submitted.

![Adoption Confirmation](screenshots/adoption_confirmation.png)

### 5. Settings Screen

Allows users to change the application's appearance and toggle dark mode.

![Settings](screenshots/settings.png)

### 6. Filter Screen

Allows users to filter breeds based on breed group and origin.

![Filters](screenshots/filters.png)

### 7. External Adoption Resource

Demonstrates opening an external adoption information website using an Android intent.

![External Adoption Resource](screenshots/external_adoption.png)

### 8. Email Adoption Enquiry

Demonstrates launching an email application with a pre-filled adoption enquiry.

![Email Intent](screenshots/email_intent.png)

---

## 🚀 Installation and Setup

### Prerequisites

- Android Studio
- Android SDK
- JDK compatible with the project's Gradle configuration
- Android emulator or physical Android device
- Internet connection

### Steps

1. Clone the repository:

```bash
git clone https://github.com/Koushik283/PetAdoptionExplorer.git
```

2. Open the project in Android Studio.

3. Allow Gradle to sync and download the required dependencies.

4. Configure your API key for The Dog API as required by the project.

5. Connect an Android device or start an emulator.

6. Build and run the application.

---

## 👥 Team Members and Contributions

### Member 1 – Koushik
**SRN:** PES1UG24CS128

- Contributed to the core application implementation.
- Worked on application navigation and integration.
- Implemented and improved the adoption request workflow.
- Worked on Android intent integration for external actions.
- Contributed to project integration, testing, and debugging.

### Member 2 – Gautam
**SRN:** PES1UG24CS169

- Contributed to application development and implementation.
- Worked on UI components and feature development.
- Assisted with integration and testing of application functionality.

### Member 3 – Manasvi
**SRN:** PES1UG24CS261

- Contributed to UI development and application functionality.
- Worked on user interaction components and application usability.
- Assisted with testing and debugging.

### Member 4 – Sahasra
**SRN:** PES1UG24CS267

- Contributed to application development and feature implementation.
- Assisted with application testing and validation.
- Worked on improving the overall user experience.

---

## 🔮 Future Enhancements

- Display adoption request history.
- Add more animal categories.
- Provide additional filtering options.
- Integrate a verified animal shelter directory.
- Improve adoption request handling with a backend service.
- Add user accounts and personalized recommendations.
- Enhance accessibility and UI experience.

---

## 📚 References

- **The Dog API:** https://thedogapi.com/
- **Android Developers:** https://developer.android.com/
- **Jetpack Compose:** https://developer.android.com/develop/ui/compose
- **Retrofit:** https://square.github.io/retrofit/
- **Room Database:** https://developer.android.com/training/data-storage/room

---

## 📄 License

This project was developed as an academic project. 
