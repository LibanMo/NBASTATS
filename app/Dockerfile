# Step 1: Build the React application
FROM node:14 AS build-step

WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . . 
COPY .env ./  
RUN npm run build

# Step 2: Serve the app with Nginx
FROM nginx:stable-alpine

COPY --from=build-step /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
