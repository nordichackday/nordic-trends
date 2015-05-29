all: build run

build:
	docker build -t rsssource .

run:
	docker run -ti -p 8080:8080 \
		-v $$PWD:/code rsssource
