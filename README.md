# Spring boot - Docker Multi-Stage build

- [What is a multi-stage build?](https://vsupalov.com/docker-multi-stage-advantages/)
- [Official documentation](https://spring.io/guides/topicals/spring-boot-docker/)
## How to use docker in spring boot (back-end):

1.You write line this in terminal
   
- Run command

```bash
docker-compose -f docker-compose.prod.yml up -d
```
- Stop command
```bash
docker-compose -f docker-compose.prod.yml stop
```
- Destroy command
```bash
docker-compose -f docker-compose.prod.yml down -v
```

## Build tag
_Note: To reduce build time please enable DOCKER_BUILDKIT=1_

```bash
export DOCKER_BUILDKIT=1 # enable Docker buildkit (for Linux)
```
1.You must export Docker buildkit
```bash
docker build -t levidocker123/music_player_docker:latest .
```
### Docker
1.Docker push
```bash
docker push levidocker123/music_player_docker:latest
```
## MYSQL
** Export File: You must copy export data in file xxx.sql then you paste into mysql:8.0 container
```bash
docker exec -ti mysql-docker mysql -u root -p
#password:870124zxc (in file docker-compose)
```
1.USE Dump
```bash
docker exec mysql-docker /usr/bin/mysqldump -u root -p dbmusic > dump_playmusic.sql
```
2.Add create database into file dump (if null)
```bash
CREATE DATABASE  IF NOT EXISTS `dbmusic` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dbmusic`;
```