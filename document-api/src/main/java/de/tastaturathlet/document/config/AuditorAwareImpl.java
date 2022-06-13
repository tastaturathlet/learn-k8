package de.tastaturathlet.document.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

  private final static String AUDITOR_USER = "system";

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of(AUDITOR_USER);
  }
}