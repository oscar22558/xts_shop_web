FROM nginx:alpine as base

FROM base as production
COPY ./default.conf /etc/nginx/conf.d/default.conf

FROM base as test
COPY ./default-test.conf /etc/nginx/conf.d/default.conf