build:
	docker build -t goals .

run:
	docker run -d -p 8080:8080 --name goals goals

stop:
	docker stop goals
	docker rm goals

clear:
	docker rmi goals

prune: stop clear

restart: prune build run