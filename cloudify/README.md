# Cloudify

Read formatted JSON data and generete JSON data formatted tag cloud.

## Tech

This is a simple Flask app, packaged in a Docker container.
It is started in nginx and gunicorn in production mode.

## Dev

You need Docker, and Make.

Type `make` and surf to [http://0.0.0.0:3000](http://0.0.0.0:3000).

## Endpoints

* `/random` - Return a random string, a mocked endpoint.
* `/tags/<n>`- The real endpoint that uses rsssource as input, hide tags with n items.
