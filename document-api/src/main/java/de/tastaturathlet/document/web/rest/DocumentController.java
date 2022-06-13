package de.tastaturathlet.document.web.rest;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

import de.tastaturathlet.document.domain.Document;
import de.tastaturathlet.document.repository.DocumentRepository;
import de.tastaturathlet.document.web.rest.util.HeaderUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

  private final Logger log = LoggerFactory.getLogger(DocumentController.class);

  private static final String HEADER_X_TOTAL_COUNT = "X-Total-Count";
  @Value("${spring.application.name}")
  private String applicationName;

  private DocumentRepository documentRepository;

  public DocumentController(DocumentRepository documentRepository) {
    this.documentRepository = documentRepository;
  }

  @GetMapping("/{id}")
  ResponseEntity<Document> getDocument(@PathVariable Long id) {
    log.debug("REST request to get AlertingConfig : {}", id);
    Optional<Document> document = documentRepository.findById(id);
    if (document.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    HttpHeaders headers = new HttpHeaders();
    headers.add(HEADER_X_TOTAL_COUNT, "1");
    return ResponseEntity.ok().headers(headers).body(document.get());
  }

  @GetMapping("/")
  public ResponseEntity<List<Document>> getDocuments(Pageable pageable) {
    log.debug("REST request to get Documents by Page: {}", pageable.getPageNumber());
    Page<Document> page = documentRepository.findAll(pageable);
    HttpHeaders headers = new HttpHeaders();
    headers.add(HEADER_X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Document> createDocument(@Validated @RequestBody Document document)
      throws URISyntaxException {
    log.debug("REST request to save Document : {}", document);

    Document result = documentRepository.save(document);

    return ResponseEntity.created(new URI("/api/document/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME,
            result.getId().toString()
        ))
        .body(result);
  }

  /**
   * {@code PUT  /document} : Updates an existing Document.
   */
  @PutMapping("/")
  public ResponseEntity<Document> updateDocument(@Validated @RequestBody Document document) {
    log.debug("REST request to update AlertingConfig : {}", document);
    if (document.getId() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Document result = documentRepository.save(document);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
            document.getId().toString()))
        .body(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
    log.debug("REST request to delete Document : {}", id);
    documentRepository.deleteById(id);
    return ResponseEntity.noContent().headers(
            HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
        .build();
  }
}
