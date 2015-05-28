FROM ubuntu:vivid
MAINTAINER Stefan Berggren

RUN apt-get update && apt-get install -y nginx python python-pip python-dev
RUN pip install Flask==0.10.1 && pip install gunicorn flask-cors

ADD nginx.conf /etc/nginx/nginx.conf
ADD app.py /usr/share/nginx/html/
ADD entrypoint /usr/local/bin/entrypoint
RUN chmod +x /usr/local/bin/entrypoint

CMD /usr/local/bin/entrypoint
