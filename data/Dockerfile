FROM postgres:latest
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=hackathon

# Add the content of the `db-scripts` directory to your image
COPY ./db-scripts/ ./docker-entrypoint-initdb.d/

# add port
EXPOSE 5432

