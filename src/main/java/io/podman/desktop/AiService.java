package io.podman.desktop;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(chatMemoryProviderSupplier = RegisterAiService.NoChatMemoryProviderSupplier.class)
public interface AiService {

    static final String PROMPT = """
        Your job is to produce a final summary
        We have provided an existing summary up to a certain point: {existing_answer}
        We have the opportunity to refine the existing summary
        (only if needed) with some more context below.
        ------------
        {text}
        ------------
        Given the new context, refine the original summary
        If the context isn't useful, return the original summary.
        Only use bullet points.
        Dont ever go beyond 10 bullet points.
            """;
@UserMessage(PROMPT)
String request(String text, String existing_answer);
}
