FROM node:18-alpine3.15 as base
WORKDIR /www

FROM base as development
ENV NODE_ENV development
COPY package.json ./
COPY yarn.lock ./
RUN yarn install
COPY . .
CMD ["yarn", "start"]


FROM base as builder
ENV NODE_ENV production
COPY package.json ./
COPY yarn.lock ./
RUN yarn install --production
COPY . .
RUN yarn build

FROM nginx:alpine as production
ENV NODE_ENV production
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /www/build /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]