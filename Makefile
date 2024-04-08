DOCS_BASE_PATH ?= "/"


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

stop:
	docker-compose stop

docs: clean_docs build_docs

clean_docs:
	rm -rf pages

build_docs:
	@echo WEEE $(DOCS_BASE_PATH)
	pnpm --prefix presentation/ install --frozen-lockfile
	pnpm --prefix presentation/ build --base $(DOCS_BASE_PATH)
	mkdir -p docs
	mv presentation/dist/* docs/
	$(MAKE) -C backend javadoc
	mkdir -p docs/javadoc
	mv backend/docs/* docs/javadoc/

.PHONY: all build run open stop docs clean_docs build_docs
