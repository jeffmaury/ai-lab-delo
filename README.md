# summarize

The purpose of this demo is to AI enable an application that retrieves Podman Desktop release notes.

In the first iteration, the release notes is retrieved and displayed as is in the UI.
In the second iteraction, the release notes is summarized by an LLM before it is returned.

# Initial iteraction

For a terminal, run:

```sh
mvnw quarkus:dev
```

Then from a browser, open http://localhost:8080/rn

## Second iteration: enable AI

In Podman AI Lab, start an inference server and get the port used by that inference server

Then, in the file src/main/resources/application.properties, change:

`aiEnabled=true`

and update the port in the `quarkus.langchain4j.openai.base-url` property.

Then from a browser, open http://localhost:8080/rn

The displayed info should not be summarized.