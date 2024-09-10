FROM ubuntu:latest
LABEL authors="lisea"

ENTRYPOINT ["top", "-b"]