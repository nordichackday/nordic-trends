all: build

build:
	cd cloudify && ${MAKE} build
	cd rsssource && ${MAKE} build
