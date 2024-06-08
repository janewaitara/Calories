# Calories

A simple multimodule app that fetches Food composition details from [Calorie Ninja](https://calorieninjas.com/api) based on searches. 

### Features

As a user, I can

- Search for a food to see it's composition
- Click on the search results to see more details

# Designs

|                Initial Idle state                      |               Idle state with recent searches               |  
|:------------------------------------------------------:|:-----------------------------------------------------------:|
| <img src="screenshots/ui/empty_state.png" width="200" alt="Idle Screen"> |  <img src="screenshots/ui/empty_state_recent_searches.png" width="200" alt="Idle state">  |

|                     Search Results                     |                       Food details                        |  
|:------------------------------------------------------:|:---------------------------------------------------------:|
| <img src="screenshots/ui/search_results.png" width="200" alt="Search results"> | <img src="screenshots/ui/details.png" width="200" alt="Food details"> |

|                     Error State                        |                                No Results Found                                            |
|:------------------------------------------------------:|:-------------------------------------------------------------------------------------------|
| <img src="screenshots/ui/error_state.png" width="200" alt="Error state"> |  <img src="screenshots/ui/no_results.png" width="200" alt="No results found"> |

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

