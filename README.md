# ES-opensky group ->L38(P3)

ES-opensky is a web-site that displays real-time data concerning arrivals on Frankfurt international airport. 
For the backend we used Springboot(java) and create a docker which contains a container that runs a mysql database(see the commands below)


Database runs on port: 3306;
Api runs on port: 8080;

To run the app run the following commands by order:


**1-Creating MySQL database using docker container:**
----------------------------------------

  > docker pull mysql:latest

  > sudo docker run --name demo -d -p3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql:latest
  
  > sudo docker exec -it demo /bin/bash
  
  > mysql -uroot -ppassword
  
  > CREATE DATABASE flights_db;
  
  > USE flights_db;


**2 - Running KAFKA broker:**
-----------------------------------------------

  1 - download the kafka file from https://www.apache.org/dyn/closer.cgi?path=/kafka/2.4.1/kafka_2.12-2.4.1.tgz;

  2 - extract folder from downloaded file:
  
    > tar -xzf kafka_2.12-2.4.1.tgz;
  
    > cd kafka_2.12-2.4.1;
      
  3 - inside the extracted folder run the following commands on separate terminals:
  
    > bin/zookeeper-server-start.sh config/zookeeper.properties;

    > bin/kafka-server-start.sh config/server.properties.
  
  

**3- RUN API:**
**May vary depending on chosen IDE:**
-----------------------------------------------

Go to the complete/src/main/java/com/example/consumingrest folder
which contains the ConsumingRestApplication.java (main class) and run it (on VS Code right click on the file and RUN)

Once the API is running open a web browser and type:

**localhost:8080**

