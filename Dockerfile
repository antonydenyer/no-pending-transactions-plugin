ARG BESU_VERSION
FROM hyperledger/besu:$BESU_VERSION
COPY --chown=besu:besu plugins/no-pending-transactions-plugin-1.0-SNAPSHOT-all.jar /opt/besu/plugins/

ENTRYPOINT ["besu"]
