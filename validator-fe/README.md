# Validator front-end

## Front-end web application that renders and list the customers information in tabular form

## Install required dependencies

### ```npm install```

## To run the unit tests:

### ```npm run test --watch``` 

## To run the project:

### ```npm start```

## To dockerize the project:

- ```docker build -t validator-fe:dev .```

- ```docker run -it --rm  -v ${PWD}:/app  -v /app/node_modules -p 3001:3000 -e CHOKIDAR_USEPOLLING=true validator-fe:dev```

- Open in browser port 3000
