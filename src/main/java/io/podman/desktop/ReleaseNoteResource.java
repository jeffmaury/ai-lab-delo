package io.podman.desktop;

import io.quarkus.qute.Template;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/rn")
public class ReleaseNoteResource {

    @Inject
    GitService gitService;

    @Inject
    Template index;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello(@QueryParam("version") String version) {
        String rn = gitService.getReleaseNote(version);
        return index.data("content", rn).render();
    }
}
