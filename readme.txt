docker build -t eduardooliveira/tomcat-server:1.0 .
&& docker run -p 8383:8080 -it eduardooliveira/tomcat-server:1.0


https://imasters.com.br/banco-de-dados/postgresql-docker-executando-uma-instancia-e-o-pgadmin-4-partir-de-containers


docker run --name teste-postgres --network=postgres-network -e "POSTGRES_PASSWORD=Postgres2018!" -p 5432:5432 -v c:/PostgreSQL:/var/lib/postgresql/data -d postgres
docker run --name teste-pgadmin --network=postgres-network -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=eoalcantara@gmail.com" -e "PGADMIN_DEFAULT_PASSWORD=PgAdmin2018!" -d dpage/pgadmin4

docker-compose down --remove-orphans



version: '3'

services:
  db:
    image: mysql:5.7
    container_name: db
    environment:
      MYSQL_ROOT_PASSWORD: jsfprimefaces
      #MYSQL_DATABASE: cursojsfprimefaces
      #MYSQL_USER: root
      #MYSQL_PASSWORD: jsfprimefaces
    ports:
      - "3306:3306"
    volumes:
      - dbdata:/var/lib/mysql
  phpmyadmin:
    image: phpmyadmin/phpmyadmin
    container_name: pma
    links:
      - db
    environment:
      PMA_HOST: db
      PMA_PORT: 3306
      PMA_ARBITRARY: 1
    restart: always
    ports:
      - 8081:80
  web:
    depends_on:
      - db
    image: maven:3.5.3-jdk-8   
    build:     
      dockerfile: Dockerfile
    ports:
      - '8082:8080'
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: jsfprimefaces
      #MYSQL_USER: testuser
      MYSQL_PASSWORD: jsfprimefaces
volumes:
  dbdata:







    version: '3'

services:
  db:
    image: mysql:5.7
    container_name: db    
    environment:
      MYSQL_ROOT_PASSWORD: jsfprimefaces
      MYSQL_DATABASE: cursojsfprimefaces
      #MYSQL_USER: root
      #MYSQL_PASSWORD: jsfprimefaces
    ports:
      - "3306:3306"
    volumes:
      - dbdata:/var/lib/mysql 

    networks:
      - employee-mysql  

  phpmyadmin:
    image: phpmyadmin/phpmyadmin
    container_name: pma
    links:
      - db
    environment:
      PMA_HOST: db
      PMA_PORT: 3306
      PMA_ARBITRARY: 1
    restart: always
    ports:
      - 8081:80 

    networks:
      - employee-mysql

  web:
     container_name: web

     networks:
      - employee-mysql

     depends_on:
       - db
     build:     
       dockerfile: Dockerfile      
     ports:
       - '8080:8080'
     environment:      
       MYSQL_ROOT_PASSWORD: jsfprimefaces
       MYSQL_DATABASE: jsfprimefaces
       
volumes:
  dbdata:

networks:
  employee-mysql: 
