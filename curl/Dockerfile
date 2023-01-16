FROM curlimages/curl:latest


COPY script.sh /tmp/script.sh


USER root

RUN chmod +x tmp/script.sh
USER 1001
ENTRYPOINT ["/tmp/script.sh"]

