FROM openjdk:8
COPY ./out/production/HelloWorld/ /tmp
WORKDIR /tmp
EXPOSE 8080
ENV TZ Europe/Moscow
ENTRYPOINT ["java","HelloWorld"]
