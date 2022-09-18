FROM node:18-alpine3.15 as base
WORKDIR /www
COPY package.json ./
COPY yarn.lock ./
RUN yarn install
COPY . .

FROM base as production
RUN yarn build

FROM base as dev
CMD ["yarn", "start"]