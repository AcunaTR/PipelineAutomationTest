docker run --rm \
           -v "pwd/:/build" \
           --workdir /build/Janra.Jowi \
           maven:3.5.2-jdk-8 mvn compile