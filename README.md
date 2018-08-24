# Slack-Blockchain-app

Slack integration App that allows you to make transactions easily within the Slack.


[![codecov](https://codecov.io/gh/Nikola-Popov/slack-blockchain-app/branch/master/graph/badge.svg)](https://codecov.io/gh/Nikola-Popov/slack-blockchain-app)

## Table of Contents 

- [Features](#features)
- [Project structure](#project-structure)
- [Development](#development)
  - [Installation and local development scenario](#installation-and-local-development-scenario)
  - [Contributing](#contributing)
- [License](#license)


---

## Features
TBD

---

## Project structure
```text
├── slack-blockchain        // The parent maven project 
|  ├── commons              // Contains common configurations, beans and constants for the whole project 
|  ├── slack-app            // Interacts with slack
|  ├── transaction-broker   // Interacts with coinbase 
```

---

## Development
### Installation and local development scenario

The following prerequisites must be fulfilled:
   - you need to be a contributor to the project. Otherwise you won`t be able to configure the app
   - download [ngrok](https://ngrok.com/) allowing secure introspectable tunnels to localhost

### Clone

- Clone this repo to your local machine using one of the links
   - SSH `git@github.com:Nikola-Popov/slack-blockchain-app.git`
   - HTTPS`https://github.com/Nikola-Popov/slack-blockchain-app`

### Setup

- Navigate to the cloned directory.

```shell
$ cd slack-blockchain
```

- In order to build the project and run all the tests execute.

```shell
$ mvn clean install
```
- After the build is successful we can run the project by navigating to the slack-app submodule and executing it from there,

```shell
$ cd slack-app
$ mvn spring-boot:run
```
- Open another terminal window and run the ngrok. By default the application runs on port 8080 so ngrok with map this port.

```shell
$ ngrok
```
- Finally, go to https://api.slack.com/apps and if you have the right acess you should see the app there. Manually configure the`Slash Commands` and the `Interactive components` request urls with the url provided by ngrok.


### Contributing
1. 🍴 Fork this repo. 
2. 🔨 HACK AWAY! But don`t forget to write tests! :)
3. 🔃 Create a new pull request using the <a href="https://github.com/Nikola-Popov/slack-blockchain-app/compare/" target="_blank">`compare changes tool`</a>.

---

## License

TBD
