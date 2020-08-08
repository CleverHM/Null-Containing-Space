# develop stage
FROM node:lts-alpine as develop-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

#build stage
FROM develop-stage as build-stage
RUN npm run build

# production stage
EXPOSE 80
CMD ["http-server", "dist"]
