Notes:

The provided public API for fetching trending Github repositories is already available and functional.

In the project the GithubApiService assumes that the API endpoint for fetching trending repositories is correctly implemented and returns the expected response.

In the project the repositories are fetched from the API on demand, but there is no automatic periodic refresh of the data. The user can manually refresh the data by triggering a new API call.

The project includes a RecyclerView with a custom adapter and view holder to display the trending repositories. The layout and design of the app adhere to the provided specifications.

The project includes support for dark mode, allowing the app to adapt to the user's system-wide dark mode settings.

The project utilizes Kotlin as the programming language and follows the MVVM architecture pattern, separating concerns into separate layers such as data layer (repository), presentation layer (view models), and UI layer (activities/fragments).
Assumption: The app includes basic error handling and displays appropriate error messages when API calls fail.

The project includes some unit tests to demonstrate the testing approach. However, the coverage and thoroughness of the tests may vary, and additional tests may be needed based on the complexity and requirements of the actual app.

