This is the backend for the Swedish event creation app.

The front end is located here: https://github.com/erik132/svenska-fest

If you wish to run this yourself with little effort you can use the materials in dockerItems folder to make an image and container out of this project and the frontend.

Using this project, the frontend project and the files in dockerItems make the following file/folder structure:

```
svenska-fest-docker:
    svenska-fest-back (git clone https://github.com/erik132/svenska-fest-back.git) 
    svenska-fest (git clone https://github.com/erik132/svenska-fest.git) 
    docker 
    Dockerfile 
    .dockerignore 
```
Once you have the file structure above run the following 3 commands.

cd svenska-fest-docker \
docker build -t svenska-fest ./ \
docker run -d -p 80:80 --name svenska-fest -e POSTGRES_USER=svenska_man -e POSTGRES_PASSWORD=abbarules -e ADMIN_USERNAME=Sven333 -e ADMIN_PASSWORD=swedishCookies100 svenska-fest

In the final command you can specify the postgres database user and password and the administrator account username and password. The postgres database user will be used inside the container and does not affect the use of the webpage itself. The Admin account is what will be used to log into the webpage after the container starts up.

The app also contains 2 inbuilt accounts Nils123 and Johan123, where the first has the ADMIN role and the second has the STANDARD role. Passwords can be provided when asked.

The container takes about 30 seconds to start up after which the webpage is accessible at localhost assuming you did not change the port configuration.

If you wish the add additional information for the admin account then you could also add all the following as environment variables in the docker run command:
ADMIN_EMAIL
ADMIN_FIRST_NAME
ADMIN_LAST_NAME
ADMIN_ID_CODE