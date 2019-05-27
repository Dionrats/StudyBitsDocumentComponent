FROM openjdk:11-jdk-stretch

VOLUME /tmp

ARG DEPENDENCY=target/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

RUN apt-get update \
    && apt-get install -y software-properties-common \
                apt-transport-https \
                maven \
                git \
                curl \
    && apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 68DB5E88 \
    && add-apt-repository "deb https://repo.sovrin.org/sdk/deb xenial stable" \
    && apt-get update \

ARG LIBINDY_VERSION=1.6.6

RUN apt-get install -y libindy=$LIBINDY_VERSION

ENTRYPOINT ["java","-cp","app:app/lib/*","nl.quintor.studybits.documents.DocumentsApplication"]