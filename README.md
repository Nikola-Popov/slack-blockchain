# Slack-Blockchain-app

Slack application that allows you to make transactions easily within Slack. All this functionality is served as a simple, intuitive and native for Slack user interface. 

To begin with, please refer to the [Manual](#manual) on how to use the application.

[![Build Status](https://travis-ci.com/Nikola-Popov/slack-blockchain-app.svg?branch=master)](https://travis-ci.com/Nikola-Popov/slack-blockchain-app)

## Table of Contents 

- [Features](#features)
- [Manual](#manual)
- [Project structure](#project-structure)
- [Development](#development)
  - [Installation and local development scenario](#installation-and-local-development-scenario)
  - [Contributing](#contributing)
- [License](#license)


---

## Features

![](https://media.giphy.com/media/8YyZabH4jzHFb58kO6/giphy.gif)

---

## Manual
As by design this application is tighly coupled with [coinbase](https://www.coinbase.com/dashboard) mainly because transactions made between coinbase users are made off-the-blockchain - in brief without paying transaction fees. So be sure to sign up there if you haven`t done this already.

In order to use the app properly one should `/configure [coinbase email address]` prior to start sending funds. One can       change the email as many times as he/she wants. But keep in mind that one email is mapped to a single slack user (you can     cannot have two or more emails configured at the same time). 
After a successful configuration one should receive the following message: `Coinbase email configured successfuly!
You are now able to make transactions.`
 
Finally, typing `/transaction` in the slack's text box and filling all the all the required fields in the popped-up Transaction dialog will send the selected amount of funds to a selected user from this channel. If the transaction is successful one should see `All good to go! You have sucessfuly created a transaction.`, otherwise an error message is displayed.

---

## Project structure
```text
├── slack-blockchain        // The parent maven project 
|  ├── commons              // Contains common configurations, beans and constants for the whole project 
|  ├── slack-app            // The core submodule. Interacts with slack and handles processing. 
|  ├── transaction-broker   // Interacts with coinbase. Realiable for the authorization and processing of the transactions
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

---
## License

TBD
