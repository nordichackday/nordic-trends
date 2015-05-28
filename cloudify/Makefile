all: build

build:
	docker build -t cloudify .

dev:
	docker run -tip 3000:80 \
		-v $$PWD:/usr/share/nginx/html \
		cloudify entrypoint
