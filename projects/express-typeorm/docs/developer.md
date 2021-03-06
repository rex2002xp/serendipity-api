# Serendipity API Developer Documentation

## Quick Start

The goal of this guide is to help you build and run Serendipity's API.

### Step 1. Set up the Development Environment 

You need to set up your development environment before you can do anything.

Open a terminal window and install [Node.js (and npm)](https://nodejs.org/en/download/) and [git](https://git-scm.com/) if they are not already on your machine.

> Verify that you are running at least Node.js version 8.x or greater and npm version 5.x or greater by running node -v and npm -v in a terminal/console window. Older versions produce errors, but newer versions are fine.

### Step 2. Clone the project 

Change the current working directory to the location where you want the cloned directory to be:

```
cd ~/workspaces
```

Clone the project by running the following command:

```
git clone https://github.com/Robinyo/serendipity-api
```

### Step 3: Serve the application 

Go to the project directory, install the project's dependencies and launch the server:

```
cd serendipity-api/projects/express-typeorm
npm install
npm start
```

### Step 4: Seed the Database

In another terminal session: 

```
cd serendipity-api/projects/express-typeorm
ts-node ./src/database/seeds/senators/senators.ts
```

or

```
cd serendipity-api/projects/express-typeorm
ts-node ./src/database/seeds/one-hundred-individuals.ts
```

Open your browser and try:

```
http://localhost:3001/api/individuals
http://localhost:3001/api/individuals/4
http://localhost:3001/api/organisations
http://localhost:3001/docs
```

## Build Management

### TypeORM

TypeORM supports multiple configuration sources. 

For example, [ormconfig.json](https://typeorm.io/#/using-ormconfig):

```
{
  "type": "sqlite",
  "database": "serendipity.db",
  "synchronize": true,
  "migrationsRun": false,
  "logging": false,
  "entities": [
    "src/api/models/**/*.ts"
  ],
  "migrations": [
    "src/database/migrations/**/*.ts"
  ],
  "subscribers": [
    "src/api/subscribers/**/*.ts"
  ],
  "cli": {
    "entitiesDir": "src/api/models",
    "migrationsDir": "src/database/migrations",
    "subscribersDir": "src/api/subscribers"
  }
}
```

## Docker

### Build an Image

You can use the sample 
[Dockerfile](https://github.com/Robinyo/serendipity-api/tree/master/projects/express-typeorm/Dockerfile) to build an 
application image:

```
docker build -t robinyo/serendipity-api .
```

**Note:** Replace `robinyo` with your Docker Hub username.

Where is your image? It’s in your machine’s local Docker image registry:

```
docker image ls
```

You should see output like:

```
REPOSITORY                TAG                 IMAGE ID            CREATED             SIZE
robinyo/serendipity-api   latest              b0b3042f59ab        12 seconds ago      1.01GB
```

### Run the Image

For example:

```
docker run --name serendipity-api -p 3001:3001 robinyo/serendipity-api
```

or

```
docker start --interactive serendipity-api
```

To list all running containers:

```
docker container ls
```

To stop a container:

```
docker container stop [name]
```

For example:

```
docker container stop serendipity-api
```

To remove a container:

```
docker container rm CONTAINER_ID
```

To delete an image:

```
docker image rm IMAGE_ID --force
```

To remove all unused objects:

```
docker system prune
```

## Docker Compose

### Build an Image

You can use the sample 
[Dockerfile](https://github.com/Robinyo/serendipity-api/tree/master/projects/express-typeorm/Dockerfile) and 
[docker-compose.yml](https://github.com/Robinyo/serendipity-api/tree/master/projects/express-typeorm/docker-compose.yml) to build an 
application image:

```
docker-compose build
```

### Run the Image

For example:

```
docker-compose up
```

## Resources

### Node.js Resources

* GitHub: [Node.js Best Pactices](https://github.com/i0natan/nodebestpractices)
* GitHub: [A starter template for TypeScript and Node.js](https://github.com/microsoft/TypeScript-Node-Starter)

### TypeORM Resources

* GitHub: [TypeORM - ORM for TypeScript and JavaScript](https://github.com/typeorm/typeorm)
* TypeORM.io: [Getting Started Guide](https://typeorm.io/#/)

### Additional TypeORM Resources

* GitHub: [W3Tech - Express TypeScript Boilerplate](https://github.com/w3tecch/express-typescript-boilerplate)
* codeburst.io: [TypeORM By Example](https://codeburst.io/typeorm-by-example-part-1-6d6da04f9f23)

### Docker Resources

* GitHub: [Docker and Node.js Best Practices](https://github.com/nodejs/docker-node/blob/master/docs/BestPractices.md)
* jdlm.info: [Lessons from Building Node Apps in Docker](https://jdlm.info/articles/2019/09/06/lessons-building-node-app-docker.html)
