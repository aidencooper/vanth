package net.aidencooper.vanth.media.system;

import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import net.aidencooper.vanth.media.api.SystemApi;
import net.aidencooper.vanth.media.api.model.Health;

@RestController
public class HealthController implements SystemApi {
    private final BuildProperties buildProperties;

    public HealthController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Override
    public ResponseEntity<Health> getHealth() {
        return ResponseEntity.ok(new Health(Health.StatusEnum.OK, this.buildProperties.getVersion()));
    }
}
