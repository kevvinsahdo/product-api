.PHONY: build up

build:
	@docker image build -t product-api .

up:
	@docker run -p 8080:8080 product-api:latest