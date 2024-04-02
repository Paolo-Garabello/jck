default: all

all: build run open

build:
	docker-compose build

run:
	docker-compose up -d

ifeq ($(shell uname),Darwin)
open:
	open http://localhost:3000
else ifeq ($(shell uname),Linux)
open:
	xdg-open http://localhost:3000
else
open:
	echo "Please open http://localhost:3000 manually."
endif

stop: docker-compose stop

.PHONY: all build run open stop
