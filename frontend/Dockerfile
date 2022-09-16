FROM node:18-alpine3.15
WORKDIR /www
COPY package.json ./
COPY yarn.lock ./
RUN yarn install --production
COPY . .
RUN yarn build