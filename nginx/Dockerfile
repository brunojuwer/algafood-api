FROM nginx:latest

RUN rm /etc/nginx/conf.d/default.conf
RUN touch /etc/nginx/conf.d/default.conf
RUN echo "server { location / { proxy_pass http://algafood-api:8081; } }" >> /etc/nginx/conf.d/default.conf