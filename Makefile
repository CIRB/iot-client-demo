PORT=8080
-include .env

DOCKER_ROOT=docker
DOCKER_ENV=COMPOSE_PROJECT_NAME=application PORT=$(PORT)

build: ## Build the application as a docker image
	mvn clean package -Pdocker

build-sonar: ## Build the application (like build does) and run sonar analysis (Jenkins oriented target)
	mvn clean package sonar:sonar -Pdocker -Dsonar.host.url=$(SONAR_HOST_URL) -Dsonar.jdbc.url=$(SONAR_JDBC_URL) -Dsonar.jdbc.username=$(SONAR_JDBC_USERNAME) -Dsonar.jdbc.password=$(SONAR_JDBC_PASSWORD)

test: ## Run acceptance testing with mvn verify
	mvn verify -Pfunctional-acceptance -Dtest.acceptance.dockerEnv.port=$(PORT)

run: ## Run the application
	java -jar target/application.jar --server.port=$(PORT) --application.documentation.enabled=true

up: ## Start the dockerized local env using docker/docker-compose.yml
	cd $(DOCKER_ROOT) && $(DOCKER_ENV) docker-compose -f application-compose.yaml pull --ignore-pull-failures && $(DOCKER_ENV) docker-compose -f application-compose.yaml up -d

down: ## Stop the dockerized local env using docker/docker-compose.yml
	cd $(DOCKER_ROOT) && $(DOCKER_ENV) docker-compose -f application-compose.yaml down

help: ## This help dialog.
	@echo "Usage: make [target]. Find the available targets below:"
	@echo "$$(grep -hE '^\S+:.*##' $(MAKEFILE_LIST) | sed 's/:.*##\s*/:/' | column -c2 -t -s :)"
