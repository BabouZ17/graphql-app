# A spring boot application using GraphQL

## Run the application
```
docker-compose up --build
```

## REST
You can either curl or use postman on http://localhost:8080

### List Studios
![missing image](./docs/get_studios.png)

### Get a Studio
![missing image](./docs/get_a_studio.png)

### Create a Studio
![missing image](./docs/save_studio.png)

### List Games
![missing image](./docs/get_games.png)

### Get a Game
![missing image](./docs/get_a_game.png)

### Create a Game
![missing image](./docs/save_game.png)

## Graphiql 
To play with the graphql engine, open http://localhost:8080/graphiql?path=/graphql in your browser.

### Examples:
![missing image](./docs/graphiql.png)

ps: ids of resources change on every restart.