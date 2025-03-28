# Exchange Rate list
Small app to track exchange rates, that uses open api OpenExchangeRates. You can add and remove
currencies. Update interval on home screen is 5 sec

# Main purpose
The main purpose was to build app with clean architecture. As functionality isn't big
I didn't create actual modules, just used packages - data, domain, presentation.

# Libraries and Technologies
- Async operations - Kotlin Coroutines
- Data storage - Room
- DI - Koin
- UI - Jetpack Compose
- Navigation - Compose Navigation
- Network connection - Retrofit

# Next steps to improve
- add unit tests
- add search and error handling (my priority was architecture, not UI)
- add loading animation 
