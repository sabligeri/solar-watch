## PROJECT: SOLAR WATCH

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
    <li><a href="#endpoints">Endpoints</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


Solar Watch API is a Spring Boot application that provides endpoints for managing users and retrieving solar events based on city and date.


<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![Java][Java]][Java-url]
* [![Spring Boot][Spring-Boot]][Spring-Boot-url]
* [![Docker][Docker]][Docker-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

* Java 17
* Maven
* Docker

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/solar-watch-api.git
    cd solar-watch-api
    ```

2. Build the Docker images and start the containers:
    ```bash
    docker-compose up --build
    ```

3. Set environment variables for your database and API keys in the `.env` file:
    ```env
    DB_NAME=yourdbname
    DB_USERNAME=yourdbuser
    DB_PASSWORD=yourdbpassword
    DB_URL=jdbc:postgresql://db:5432/yourdbname
    SECRET_KEY=yourjwtsecret
    GEOCODING_API_KEY=yourgeocodingapikey
    ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ENDPOINTS -->
## Endpoints

### Admin Endpoints

| Method | Endpoint                          | Description                |
|--------|-----------------------------------|----------------------------|
| DELETE | `/admin/delete-user/{username}`   | Delete a user by username  |
| POST   | `/admin/add-admin/{userId}`       | Add admin role to a user   |
| GET    | `/admin/check`                    | Check API status           |

### User Endpoints

| Method | Endpoint                          | Description                |
|--------|-----------------------------------|----------------------------|
| POST   | `/user/register`                  | Register a new user        |
| POST   | `/user/register-admin`            | Register a new admin       |
| POST   | `/user/signin`                    | Authenticate a user        |

### Solar Watch Endpoints

| Method | Endpoint                          | Description                |
|--------|-----------------------------------|----------------------------|
| GET    | `/solarwatch/{city}/{date}`       | Get solar events for a city|

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Add Docker support
- [x] Add Admin and User management
- [x] Add Solar Event retrieval
- [ ] Add Additional Templates w/ Examples
- [ ] Add "components" document to easily copy & paste sections of the readme
- [ ] Add frontend for better user experience


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Gergely Sábli - [LinkedIn](https://www.linkedin.com/in/gergely-s%C3%A1bli-357110293/) - sabligergo@gmail.com

Project Link: [https://github.com/sabligeri/solar-watch-api](https://github.com/sabligeri/solar-watch-api)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Choose an Open Source License](https://choosealicense.com)
* [GitHub Emoji Cheat Sheet](https://www.webpagefx.com/tools/emoji-cheat-sheet)
* [Malven's Flexbox Cheatsheet](https://flexbox.malven.co/)
* [Malven's Grid Cheatsheet](https://grid.malven.co/)
* [Img Shields](https://shields.io)
* [GitHub Pages](https://pages.github.com)
* [Font Awesome](https://fontawesome.com)
* [React Icons](https://react-icons.github.io/react-icons/search)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/your-username/solar-watch-api.svg?style=for-the-badge
[contributors-url]: https://github.com/your-username/solar-watch-api/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/your-username/solar-watch-api.svg?style=for-the-badge
[forks-url]: https://github.com/your-username/solar-watch-api/network/members
[stars-shield]: https://img.shields.io/github/stars/your-username/solar-watch-api.svg?style=for-the-badge
[stars-url]: https://github.com/your-username/solar-watch-api/stargazers
[issues-shield]: https://img.shields.io/github/issues/your-username/solar-watch-api.svg?style=for-the-badge
[issues-url]: https://github.com/your-username/solar-watch-api/issues
[license-shield]: https://img.shields.io/github/license/your-username/solar-watch-api.svg?style=for-the-badge
[license-url]: https://github.com/your-username/solar-watch-api/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/your-username

[Java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.java.com
[Spring-Boot]: https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white
[Spring-Boot-url]: https://spring.io/projects/spring-boot
[Docker]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]: https://www.docker.com

