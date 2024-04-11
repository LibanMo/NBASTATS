
# Dockerized Fullstack App: Web Scraping NBA Stats and Data Plotting
This project is a fullstack web application built using Docker containers. It leverages web scraping techniques to collect NBA statistics and then visualizes the data using interactive plots.


**Project Highlights**
* Dockerized Deployment: The application is containerized using Docker, ensuring a consistent and isolated environment across different machines. This simplifies deployment and management.
* Web Scraping with NBA Stats: The application incorporates web scraping _https://www.basketball-reference.com/_  to extract valuable NBA statistics from relevant website. This allows the application to stay up-to-date with the latest data.
* Data Visualization: The scraped data is transformed and presented using interactive plots. This enables users to explore and gain insights from the NBA statistics.
* Fullstack Architecture: The projectcombines a frontend framework (**React**) for user interaction and a backend framework (**Spring Boot**) for data processing and serving the visualizations. For the Web Scraping is made using **Puppeteer** and the plotting is a smaller **Dash** application 


**Benefits:**
* Simplified Deployment and Management: Docker containers provide a portable and self-contained way to package your application, ensuring it runs consistently across different environments.
* Flexibility and Scalability: You can easily scale your application resources (CPU, memory) by modifying the container configuration.
* Interactive Data Exploration: Users can gain valuable insights from the NBA statistics through interactive visualizations.


**Prerequisites:**
* **Docker:** Ensures consistent and isolated environments for your application across different systems. Install Docker following the official instructions for your operating system: https://docs.docker.com/engine/install/
* **PostgreSQL Database (Optional):** consider using a PostgreSQL database. Installation instructions can be found here: _https://www.postgresql.org/docs/current/tutorial-install.html_
* **Postman (Optional):** While not strictly required, Postman can be a helpful tool to inspect and test web APIs or send requests during development. You can download it from: _https://www.postman.com/downloads/_
  


**Start Up:**
* Make sure to have Docker installed to run and in the project root folder run the command in the terminal: ``` docker-compose up ```
* Frontend endpoints "_/create_" to add a NBA player with Their fullname (needs to be a active player and no spelling errors) check player.csv folder inside resource folder. Example ``` { "name" : "LeBron James", "team": "LAL"} ```
  "_/_"  Displays all players saved from the database and also their latest game stats.  "_/1_" Displays a fullview of playerId 1 season stats. "_/compare_" Displays a graph that is interactive and user can see and compares game stats between players saved in the database.
