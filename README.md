# FetchTrendingRepoGithub
In this app i have implement the api for fetching trending repository from github. Using Clean Architecture MVVM, Coroutines, HILT, TDD approach. 


Assumptions and Notes:

Assumption: The provided public API for fetching trending Github repositories is already available and functional.

Note: In the implementation, the GithubApiService assumes that the API endpoint for fetching trending repositories is correctly implemented and returns the expected response.
Assumption: The API calls have a cost per call, and slightly stale data is acceptable.

Note: In the implementation, the repositories are fetched from the API on demand, but there is no automatic periodic refresh of the data. The user can manually refresh the data by triggering a new API call.
Assumption: The design and layout of the app, including the RecyclerView, are based on the provided specifications.

Note: The implementation includes a RecyclerView with a custom adapter and view holder to display the trending repositories. The layout and design of the app adhere to the provided specifications.
Assumption: The app supports both light and dark mode.

Note: The implementation includes support for dark mode, allowing the app to adapt to the user's system-wide dark mode settings.
Assumption: The app is developed using Kotlin and follows the MVVM (Model-View-ViewModel) architecture pattern.

Note: The implementation utilizes Kotlin as the programming language and follows the MVVM architecture pattern, separating concerns into separate layers such as data layer (repository), presentation layer (view models), and UI layer (activities/fragments).
Assumption: The app includes basic error handling and displays appropriate error messages when API calls fail.

Note: The implementation handles API errors by throwing an ApiErrorException and displays the error message to the user in the UI.
Note: The implementation includes some unit tests to demonstrate the testing approach. However, the coverage and thoroughness of the tests may vary, and additional tests may be needed based on the complexity and requirements of the actual app.

Note: The implementation does not include the complete UI design and resources, such as color schemes, icons, and layout variations, as these are not within the scope of this textual representation.

These assumptions and notes provide context and clarification about the implementation. It's important to review and validate these assumptions based on the specific requirements and constraints of the project at hand.
