#FROM bellsoft/liberica-openjdk-alpine:19 as BuildProject
#WORKDIR /app
#COPY ./src ./src
#RUN mkdir ./out
#RUN javac -sourcepath ./src -d out ./src/ru/geekbrains/lesson1/sample/Main.java
#
#FROM scratch as OutputFiles
#COPY --from=BuildProject /app/out /bin

FROM bellsoft/liberica-openjdk-alpine:19 as BuildProject
WORKDIR /app
COPY ./bin .
CMD java -classpath . ru.geekbrains.lesson1.sample.Main

