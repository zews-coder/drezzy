# Stage 1: Build Angular app
FROM node:18-alpine AS build

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm ci

COPY . .
RUN npm run build -- --configuration=production

# Stage 2: Serve with Nginx
FROM nginx:1.25-alpine AS serve

WORKDIR /usr/share/nginx/html
RUN rm -rf ./*

COPY --from=build /app/dist/drezzy-frontend/browser . 

# Copy custom Nginx configuration if it exists
# COPY nginx.conf /etc/nginx/nginx.conf 

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
