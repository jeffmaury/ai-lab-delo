package io.podman.desktop;

import java.net.URI;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.client.ClientBuilder;

@Singleton
public class GitService {
    private static final Map<String, String> URIs = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("1.0", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2023-05-17-release-1.0.md"),
        new AbstractMap.SimpleEntry<>("1.1", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2023-06-08-release-1.1.md"),
        new AbstractMap.SimpleEntry<>("1.2", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2023-07-12-release-1.2.md"),
        new AbstractMap.SimpleEntry<>("1.3", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2023-08-16-release-1.3.md"),
        new AbstractMap.SimpleEntry<>("1.4", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2023-09-18-release-1.4.md"),
        new AbstractMap.SimpleEntry<>("1.5", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2023-11-03-release-1.5.md"),
        new AbstractMap.SimpleEntry<>("1.6", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2023-12-18-release-1.6.md"),
        new AbstractMap.SimpleEntry<>("1.7", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2024-01-24-release-1.7.md"),
        new AbstractMap.SimpleEntry<>("1.8", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2024-03-07-release-1.8.md"),
        new AbstractMap.SimpleEntry<>("1.9", "https://raw.githubusercontent.com/containers/podman-desktop/main/website/blog/2024-04-05-release-1.9.md")
            );

        @ConfigProperty(name="aiEnabled")
        Optional<Boolean> aiEnabled;

        @Inject
        AiService aiService;

    public String getReleaseNote(String version) {
        if (version == null) {
            version = "1.9";
        }
        var uri = URI.create(URIs.get(version));
        var rn = ClientBuilder.newClient().target(uri).request().get(String.class);
        if (aiEnabled.orElse(Boolean.FALSE)) {
            var blocks = Utils.splitString(rn, 2048);
            var response = "";
            for(var block : blocks) {
                response = aiService.request(block, response); 
            }
            return response;    
        } else {
            return rn;
        }
    }
}
