## Sample Spring Boot Project to Consume Protobuf Messages

### Prerequisites

1. Have protobuf installed ( e.g. `brew install protobuf )

## use

1. run with `./gradlew bootRun`
1. take a valid protobuf3 file and `curl -H "Content-Type:application/text" -XPOST localhost:8080/person --data-binary @person_proto_new.serialized`
