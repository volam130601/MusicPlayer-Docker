Build your springboot project with Maven Wrapper:
./mvnw spring-boot:run
This downloads the dependencies, builds the project, and starts it.

How can we run this project on Docker?
- Build your own image with Dockerfile
docker build --tag musicplayer5-docker .

To create a new tag for the image we’ve built above
docker tag musicplayer5-docker:latest musicplayer5-docker:v1.0.0

Login to your Docker Hub Account, create a repository
docker tag musicplayer5-docker:v1.0.0 levidocker123/musicplayer5-docker:v1.0.0

- Push your own image to Docker Hub
Push to Docker Hub:
docker push levidocker123/musicplayer5-docker:v1.0.0
Map port: 8085(Host) <=> 8083(Container)

- Pull and start/run a container
docker run -dp 8085:8081 \
--name musicplayer5-docker-container \
-v "$(pwd):/app" \
--network musicplayer-app-network \
levidocker123/musicplayer5-docker:v1.0.0
=====
docker run -dp 8085:8081 --name musicplayer5-docker-container -v "$(pwd):/app" --network musicplayer-app-network levidocker123/musicplayer5-docker:v1.0.0
====
Test Springboot app(you can use Web Browser, Postman):

curl --request GET \
--url http://localhost:8085/students/hello \
--header 'content-type: application/json'

Update code & rerun:
docker restart springboot-docker-container

Now create another MySQL container and put this within the same 
network with springboot-docker-container:

docker network create springboot-app-network

docker run --rm -d \
-v mysql-springboot-data:/var/lib/mysql \
-v mysql-springboot-config-deamond:/etc/mysql/conf.d \
--name mysql-musicplayer5-container \
-p 3310:3306 \
-e MYSQL_USER=lam \
-e MYSQL_PASSWORD=870124zxc \
-e MYSQL_ROOT_PASSWORD=870124zxc \
-e MYSQL_DATABASE=dbmusic \
--network musicplayer-app-network \
mysql:8.0.29
=====
docker run --rm -d -v mysql-springboot-data:/var/lib/mysql -v mysql-springboot-config-deamond:/etc/mysql/conf.d --name mysql-musicplayer5-container -p 3310:3306 -e MYSQL_USER=lam -e MYSQL_PASSWORD=870124zxc -e MYSQL_ROOT_PASSWORD=870124zxc -e MYSQL_DATABASE=dbmusic --network musicplayer-app-network mysql:8.0.29
=====
Access mysql's command line inside mysql-springboot-container:
docker exec -ti mysql-springboot-container mysql -u root -p

Now replace all command to create containers into a .yml file
=> use Docker Compose
Build Docker Compose file:
#Remove old containers:

docker rm -f mysql-musicplayer5-container musicplayer5-docker-container
docker-compose -f docker-compose.dev.yml up -d --build
