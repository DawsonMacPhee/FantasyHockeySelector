# Fantasy Hockey Selector

## Setup and Running

 * Clone the repo
 * Ensure that Maven, Docker, WSL2 (if on Windows), and Java 16 are installed
 * Run command `docker run --name aws-eb-mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8.0.11`
 * Open WSL2 and run command `mysql -h 127.0.0.1 -P 3306 -uroot -proot`
 * Run `CREATE DATABASE aws_eb_db;`
 * Return to project directory and run `./mvnw spring-boot:run`
