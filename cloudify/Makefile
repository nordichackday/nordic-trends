all: build dev

build:
	docker build -t cloudify .

dev:
	docker run -tip 3000:5000 \
		-v $$PWD:/usr/share/nginx/html \
		-e ENV=dev \
		-e API_URL=$$API_URL \
		cloudify entrypoint
