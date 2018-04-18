FROM java:8
WORKDIR /
ADD ./goals-rest/target/goals-rest.jar goals-rest.jar
EXPOSE 8080
CMD java -jar goals-rest.jar