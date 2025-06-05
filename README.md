# randomQuote

RandomQuote is an **Android** app that fetches a quote from the [Quotable API](https://github.com/lukePeavey/quotable). It is written in Kotlin and makes use of **Hilt** for dependency injection, Retrofit for networking, and **ViewModel** with data binding for UI logic. The project also adopts Material Design 3.

This repository is periodically updated with help from [OpenAI's Codex](https://openai.com/blog/openai-codex) for learning and experimentation.

## ⚡ Getting Started

1. Clone the repository
   ```bash
   git clone https://github.com/HarshitaAppTech/randomQuote
   cd randomQuote
   ```
2. Build the debug APK
   ```bash
   ./gradlew assembleDebug
   ```
   Or open the project in Android Studio.

The app currently targets **minSdk 23** and **targetSdk 32** (see `app/build.gradle`).

## ✨ Features

- Get a random quote inside the app
- Refresh the quote with a pull‑to‑refresh gesture
- *(Planned)* Daily quote notification at 10:00 AM
- *(Planned)* Customizable notification time

## 🤝 Contributing

Contributions are welcome! Fork this repository and open a pull request with your improvements.

## License

No license file has been added yet. Feel free to suggest one if you intend to contribute.
