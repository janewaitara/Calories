# Calories

A simple multimodule app that fetches Food composition details from [Calorie Ninja](https://calorieninjas.com/api) based on searches. 

### Features

As a user, I can

- Search for a food to see it's composition
- Click on the search results to see more details

### Prerequisites

You'll need to provide API key to search the food composition from the API. 

- Generate an API key from [Calorie Ninja](https://calorieninjas.com/api)
- Create new file named -> local.properties in our project root folder
- Add the API key as shown below
```
key = <YOUR_API_KEY>
```
- Build the app
- Enjoyyyyy 🎉

# Designs

|                Initial Idle state                      |               Idle state with recent searches               |                    Search Results            |                    
|:------------------------------------------------------:|:-----------------------------------------------------------:|:------------------------------------------------------:|
| <img src="screenshots/ui/empty_state.png" width="200" alt="Idle Screen"> |  <img src="screenshots/ui/empty_state_recent_searches.png" width="200" alt="Idle state">  | <img src="screenshots/ui/search_results.png" width="200" alt="Search results"> |





|                       Food details                        |                     Error State                        |                                No Results Found                                            |
|:---------------------------------------------------------:|:------------------------------------------------------:|:-----------------------------------------------------------|
| <img src="screenshots/ui/details.png" width="200" alt="Food details"> | <img src="screenshots/ui/error_state.png" width="200" alt="Error state"> |  <img src="screenshots/ui/no_results.png" width="200" alt="No results found"> |

### Packaging Structure

- `core`
    - `data`
        - aggregates the data from the network and local database
    - `domain`
        - handles business logic 
    - `local`
        - handles local caching
    - `remote`
        - Fetches data from remote sources 
    - `testing`
        - Has utility classes used for testing
    - `ui`
        - Has the app theming 
- `feature`
    - `calories`
       - handles searching and displaying of the food composition
    - `food details`
       - Displays the more food composition details 

## Tech Stack
- Tech Stack
  - [Kotlin](https://kotlinlang.org/) - Programming language for Android development.
  - [Compose](https://developer.android.com/jetpack/compose/documentation) - modern toolkit for building native Android UI
  - [Kotlin coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Executing code asynchronously.
  - [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - An asynchronous version of a Sequence, a type of collection whose values are lazily produced. Flow handles the stream of data asynchronously that executes sequentially.
  - [HILT](https://developer.android.com/training/dependency-injection/hilt-android) - a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way.
  - [Ktor Client](https://ktor.io/docs/client-create-new-application.html) - a multiplatform asynchronous HTTP client, which allows you to make requests and handle responses.
  - [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization) - Serialization/Desirialization of JSON response from network.
  - [Jetpack Datastore](https://developer.android.com/topic/libraries/architecture/datastore) - a data storage solution that allows you to store key-value pairs.

- Gradle
  * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For reference purposes, here's an [article](https://evanschepsiror.medium.com/migrating-to-kotlin-dsl-4ee0d6d5c977) explaining the migration.
  * Plugins
      - [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - creates convenient tasks in your Gradle project that run ktlint checks or do code auto format.
      
- CI/CD
  * Github Actions



# Tests

The app includes both unit and instrumented tests.

### Core

#### data

|                                CalorieRepositoryTests                                 |
|:-------------------------------------------------------------------------------------:|
| <img src="screenshots/tests/repo_tests.png" width="700" hspace="2" alt="Repo Test" /> |


#### Remote

|                                 CaloriesSearchApiTest                                  |
|:--------------------------------------------------------------------------------------:|
| <img src="screenshots/tests/api_tests.png" width="700" hspace="2" alt="Remote Test" /> |


### Presentation

#### Calories ViewModel

|                                   CaloriesScreenViewModelTest                                   |
|:-----------------------------------------------------------------------------------------------:|
| <img src="screenshots/tests/viewmodel_tests.png" width="700" hspace="2" alt="ViewModel Test" /> |

#### Calories Screen

|                                   CaloriesScreenTest                                   |
|:--------------------------------------------------------------------------------------:|
| <img src="screenshots/tests/ui_tests.png" width="700" hspace="2" alt="UI Test" /> |

