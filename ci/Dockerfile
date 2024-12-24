FROM bellsoft/liberica-openjre-alpine-musl:17

ARG SERVICE_NAME

ENV SERVICE_NAME_ENV=${SERVICE_NAME}

WORKDIR /services

COPY deploy/${SERVICE_NAME}.jar /service


ENTRYPOINT java -jar ${SERVICE_NAME_ENV}.jar