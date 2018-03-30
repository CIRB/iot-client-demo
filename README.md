# Push sound to IoT platform project README file

This project contains all the sources for the 'push-sound' project.

# Why ?

If a device provider needs to push sound data to the IoT platform he can use this code as an implementation describing how to interawt with the IoT platform.
Sound read is abstracted behind a **Separated Interface** named SoundRepository. In its current implementation, the repository simply reads a csv file which path is defined in the *application.yml* under *application.measure-location* this needs to be adapted to the user's needs.
Once the SoundData has been instantiated it can be sent to the platform using the **Separated Interface** named IoTPlatformGateway. The current implementation simply sends the data to the platform and handles the authentication for the user.

**Disclaimer**: This is an example implementation and should not be used directly in production. 

## [TL;DR]

Considering you have installed the [prerequesite](#general-prerequisites) tools `Make`, `Docker`, `Java`, `Maven`, follow these steps to get started:

- To build the project, from the root folder, run:
`make`

- To start up the application, run:
`make up`

- You can access the health status page at the following address: [`http://localhost:8080/management/health`](http://localhost:8080/management/health)

- You can also find the [API documentation](#api-documentation) at the following address: [`http://localhost:8080/docs/api-guide.html`](http://localhost:8080/docs/api-guide.html)

## General prerequisites

### Tools installation

- [Git](http://help.github.com/set-up-git-redirect)
- [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) and [docker-compose](https://docs.docker.com/compose/install/)

Be sure that your `JAVA_HOME` environment variable points to the `jdk1.8.0` folder extracted from the JDK download.

Add the following configuration to docker daemon: add "docker.cirb.lan" in the Insecure registries list.

With Docker for Windows, make sure to check the `Expose daemon on tcp://localhost:2375 without TLS` checkbox on the General Settings.

### Makefile

The following sections propose to use `Makefile` targets in the form of `make` commands to abstract development lifecycle goals from technologies (mvn, npm,...) and practical details of implementation (locations, profiles,...).

There are a few generic targets all projects agree to use such as `build`, `test`, `up`, `down`, `help`. You are free to add any target that would help you.

`make` is available by default on unix based systems, and can be installed on Windows from this [site](http://gnuwin32.sourceforge.net/packages/make.htm).

### Exclude Eclipse/Intellij configuration files from Git index

After cloning this Git project, execute the following commands to avoid considering changes in Eclipse configuration files :

```
git update-index --assume-unchanged **/**/.project
git update-index --assume-unchanged **/**/.settings/*
git update-index --assume-unchanged **/**/.classpath
git update-index --assume-unchanged **/**/*.iml
git update-index --assume-unchanged **/**/.idea/*
```

### Configure Eclipse compiler options

The [Jackson Parameter Names module](https://github.com/FasterXML/jackson-modules-java8/tree/master/parameter-names) has been activated to simplify JSON deserialization through constructors. For that reason, the method parameters names must be accessible in the compiled code, as explained [here](http://docs.oracle.com/javase/tutorial/reflect/member/methodparameterreflection.html). To enable that configuration in Eclipse, just go to `Window > Preferences > Java > Compiler` and check the `Store information about method parameters (usable via reflection)` option.

## Project structure

Describe here your project structure.

## Usage

This project is designed to be usable in both development situation and in classical maven build.

### Development Usage

In Eclipse, execute `<package>.Application` class.

Once started, the application will be available at [http://localhost:8080](http://localhost:8080). As the project is configured with [spring-boot-devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html), the application does not need to be restarted after each modification in the project source, but will be automatically reloaded by Eclipse.

### Build

From `<root_package>` project folder, execute either `make`, `make build` or `mvn clean package -Pdocker` command. The latter command will be used in all cases to run the build. The default profile will skip the docker image creation.

This build execution uses the [Maven Dockerfile Plugin](https://github.com/spotify/dockerfile-maven) to create a docker image based on a `openjdk:8-alpine` image. More information about this build execution can be found in `pom.xml` and `Dockerfile` files. See [Running with docker-compose](#running-with-docker-compose) to know how to run the built docker image.

This build results in a `target/application.jar` JAR file and a `app/application:latest` docker image. See [Running jar](#running-jar) section to know how to run directly from JAR file, or [Running with docker-compose](#running-with-docker-compose) section to know how to run the dockerized environment.

### Execution

#### Environment Configuration

The project contains a `Makefile` to execute different targets for build and execution. Execution targets enable configuring local environment. Create a `.env` file in the project root folder if you desire to override defaults. The defaults are set at the top of the Makefile. Here is an example of .env content:

```
PORT=8081
```

#### Running jar

Once the build ends successfully (see [Build](#build) section), the application can be started using `make run` or `java -jar target/application.jar` command. The latter will be used in all cases to run the application.

Once started, the application will be available at [http://localhost:8080](http://localhost:8080), or the URL corresponding to the port configured in the `.env` file.

#### Running with docker-compose

Once the build ends successfully (see [Build](#build) section), a docker environment can be executed using `make up` or `cd docker && docker-compose up -d` command. The latter command will be used in all cases to run the docker containers. The `docker-compose` configuration file is located at `docker/docker-compose.yml`.

The docker environment can be stopped using `make down` or `cd docker && docker-compose down` command. The latter command will be used in all cases.

## Acceptance tests

From `<project_root>` project folder, execute either `make test` or `mvn verify -Pfunctional-acceptance` command. The latter command will be used in all cases to run the build. The default profile will skip the acceptance tests execution.

The acceptance tests are run against a docker-compose environment. The acceptance tests results are available through `target/site/serenity/index.html` page.

## API Documentation

API documentation is generated and included into `application.jar` during build process. By default, documentation is not provided during execution, except if `application.documentation.enabled` key is set to `true`, which is the case for local environment execution (see [Running jar](#running-jar) or [Running with docker-compose](#running-with-docker-compose) sections).

If enabled, API documentation is accessible at [http://localhost:8080/docs/api-guide.html](http://localhost:8080/docs/api-guide.html).
