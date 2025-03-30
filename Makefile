.PHONY: build run clean docker-build docker-run docker-stop help

build:
	mvnw.cmd clean package

run:
	mvnw.cmd spring-boot:run

clean:
	mvnw.cmd clean

docker-build:
	docker build -t fexapp .

docker-run:
	docker run --name fexapp_container -p 8080:8080 fexapp

docker-stop:
	docker stop fexapp_container || exit 0
	docker rm fexapp_container || exit 0

help:
	@echo "Makefile Targets:"
	@echo "  build        - Build the project using Maven (mvnw.cmd clean package)"
	@echo "  run          - Run the application using Maven (mvnw.cmd spring-boot:run)"
	@echo "  clean        - Clean the Maven build (mvnw.cmd clean)"
	@echo "  docker-build - Build the Docker image with tag 'fexapp'"
	@echo "  docker-run   - Run the Docker container (mapped to port 8080, container name 'fexapp_container')"
	@echo "  docker-stop  - Stop and remove the Docker container 'fexapp_container'"
