# Bingo Multiclient Game

Welcome to the Bingo Multiclient Game repository! This project implements a Bingo game that allows multiple clients to participate using Java.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [Setup](#setup)
- [Running order](#Running-order)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Bingo Multiclient Game is designed to provide an interactive Bingo gaming experience for multiple players over a network. It's implemented in Java and utilizes middleware technologies like Remote Method Invocation (RMI) and Remote Procedure Call (RPC) to enable seamless communication between clients and the server.

## Features

- Multiplayer Bingo game functionality
- Support for multiple clients connecting to the server concurrently
- Usage of RMI and RPC for client-server communication
- Score tracking and winner determination

## Project Structure

The repository contains the following folders:

- `projet-Middleware-RMI`: Contains the source code for the RMI implementation of the Bingo game.
- `projet-Middleware-RPC`: Contains the source code for the RPC implementation of the Bingo game.

## Setup

To set up and run the Bingo Multiclient Game, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the `projet-Middleware-RMI` or `projet-Middleware-RPC` folder depending on the middleware implementation you want to use.
3. Follow the instructions to run the game.

## *Running order*
To run the Bingo Multiclient Game, follow this order:

1. Bingo Server: The server application starts first to listen for incoming client connections and provide the necessary services or resources.
2. Gateway Server: The gateway, which acts as an intermediary between clients and the server, is started next. It manages client connections and facilitates communication between clients and the server.
3. Bingo Client: Launch one or more Bingo Clients to connect to the server and participate in the game.

## Usage

To play the Bingo Multiclient Game:

1. Ensure the server is running.
2. Run the client application for each player.
3. Follow the prompts to participate in the game.
4. Enjoy the Bingo gaming experience with friends and family!

## Contributing

Contributions are welcome! If you have any ideas, bug fixes, or improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
