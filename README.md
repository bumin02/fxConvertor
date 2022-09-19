<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Project

This is a simple exchange rate system which allows the user to interact with a command line interface that allows for basic exchange rate application functionalities. More details will be avaliable below


## Getting Started

### Prerequisites
* java

* gradle
  ```sh
  brew install gradle
  ```
  For other installation method, check out https://gradle.org/install/
* jenkins


### Installation

1. Clone the repo
   ```sh
   git clone https://github.sydney.edu.au/SOT2412-COMP9412-2022S2/Soft2412_Assignment_CC_04_Wed_16_Frank_Group-3-.git
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## How To Run
To run the project, use the below command line:
   ```sh
   gradle build
   gradle run -console plain
   ```
* To run our simple currency exchange program, sign in as either User or Admin. The password for admin is "admin". Allowed attempts for correct password is 3. Once all attempts are exhausted, user will be locked out of admin and can only access non-admin options.
* **Note*** <i>The system only accepts valid inputs. In the case that an invalid date such as “aa/234/xy” is given, or that the user searches for a currency that does not exist, the user will be brought back to the options screen.</i>
* You will be prompted to choose between four options if you are a normal user or 7 if you are the admin

* **1. Convert between 2 currencies** Allows user to input 2 currencies and shows the current exchange rate.
* **2. Print top 4 currency exchange rates** will show a table with the 4 most popular currencies and their respective exchange rates to each other. An “I” next to a conversion rate indicates that the rate has increased from the day before, whereas a “D” indicates a decrease in the conversion rate.
* **3. Summarise conversion rates** will portray a menu of two currencies' statistical relationship based on their historical data, such as the pair's exchange rate minimum, maximum, mean, median and standard deviation. Enter two valid dates, the first date being older than the second date, as well as two existing currencies in the database.
* **4. Exit** Allows the user to exit the program 
* **5. ADMIN: Update Conversion Rate** is a special admin-only option. It allows the user to update a currency pair’s exchange rate at most once a day.
* **6. ADMIN: Add New Currency** is a special admin-only option. It allows the user to add a new currency into the database. The user will be prompted to provide the exchange rates from this new currency to every other existing currency.
* **7. ADMIN: Update Popular Currencies** is a special admin-only option. It allows the user to edit their top 4 favourite currencies. The user will be prompted to remove a current popular currency and replace it with another existing currency.

<!-- CONTRIBUTING -->
## Contributing

Feel free to contribute to our project! Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repository and create a pull request. You can also simply open an issue with the tag "enhancement".

1. Fork the Project
2. Create your Feature Branch (`git checkout -b yourBranch`)
3. Commit your Changes (`git commit -m 'Add some Feature'`)
4. Push to the Branch (`git push origin yourBranch`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>







