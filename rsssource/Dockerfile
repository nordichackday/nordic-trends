FROM ubuntu:vivid

RUN apt-get -y update \
	&& apt-get install -y maven
RUN apt-get -y install openjdk-8-jdk
RUN adduser \
	--home /code \
	--gecos false \
	--disabled-password \
	--disabled-login \
	code
USER code

CMD /code/start
