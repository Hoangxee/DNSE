
FROM maven:3.9.5-eclipse-temurin-11

ARG U_ID=1000
ARG G_ID=1000

# Install tools.
RUN apt-get update && apt-get install -y gnupg2
RUN apt-get update -y & apt-get install -y wget
ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get install -y tzdata

# Install Chrome.
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
RUN apt-get update
RUN apt-get install -y google-chrome-stable

RUN chown $U_ID /root

USER $U_ID:$G_ID

WORKDIR /apps/automation-test/

COPY pom.xml /apps/automation-test/
RUN mvn -B dependency:go-offline

RUN mvn clean

COPY --chown=$U_ID:$G_ID . .

CMD ["mvn", "test"]

