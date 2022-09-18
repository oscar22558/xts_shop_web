FROM node:18-alpine3.15 as base
WORKDIR /www
COPY package.json ./
COPY yarn.lock ./
RUN yarn install
COPY . .

FROM base as production
ENV NODE_ENV=production
RUN yarn build

FROM base as test
ENV NODE_ENV=test
CMD ["yarn", "start"]