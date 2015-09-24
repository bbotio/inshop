FROM dgageot/java8

RUN mkdir -p /opt/${service.name}

COPY ${project.artifactId}.zip /opt/${service.name}/

WORKDIR /opt/${service.name}/

RUN unzip ${project.artifactId}.zip

EXPOSE 8080

CMD java -cp ${service.jar}:lib/* com.inshop.service.ServiceLauncher
