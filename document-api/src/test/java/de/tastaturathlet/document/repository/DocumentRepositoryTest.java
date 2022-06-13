package de.tastaturathlet.document.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.tastaturathlet.document.config.JpaAuditingConfig;
import de.tastaturathlet.document.domain.Document;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@EnableJpaRepositories(basePackages = {"de.tastaturathlet.document.*"})
@EntityScan("de.tastaturathlet.document.domain")
@ContextConfiguration(classes = {JpaAuditingConfig.class})
public class DocumentRepositoryTest {

  @Autowired
  private DocumentRepository documentRepository;

  @Autowired
  private TestEntityManager em;

  @SpyBean
  AuditorAware auditorAware;

  @SpyBean
  DateTimeProvider dateTimeProvider;

  @BeforeEach
  public void cleanupEach() {
    documentRepository.deleteAll();
  }

  @AfterEach
  public void cleanupAfter() {
    documentRepository.deleteAll();
  }

  @Test
  public void createDocument() {
    Document document = createADocument();
    documentRepository.save(document);
    assertNotNull(document);
    assertEquals("system", document.getCreateUser());
    assertNotNull(document.getCreateDate());
  }

  @Test
  public void findDocument() {
    Document document = createADocument();
    documentRepository.saveAndFlush(document);
    Optional<Document> foundDoc = documentRepository.findById(document.getId());
    assertFalse(foundDoc.isEmpty());
    assertEquals("system", foundDoc.get().getCreateUser());
    assertNotNull(foundDoc.get().getCreateDate());
  }

  @Test
  public void findAllDocument() {
    Document docOne = createADocument();
    Document docTwo = createADocument();
    List<Document> multiDocs = new ArrayList<>();
    multiDocs.add(docOne);
    multiDocs.add(docTwo);
    documentRepository.saveAllAndFlush(multiDocs);
    List<Document> all = documentRepository.findAll();
    assertEquals(2, all.size());
  }

  @Test
  public void updateDocument() {
    Document document = createADocument();
    documentRepository.saveAndFlush(document);
    Optional<Document> foundDoc = documentRepository.findById(document.getId());
    assertFalse(foundDoc.isEmpty());
    foundDoc.get().setContentTitle("Test");
    documentRepository.saveAndFlush(document);
    foundDoc = documentRepository.findById(document.getId());
    assertFalse(foundDoc.isEmpty());
    assertEquals("Test", foundDoc.get().getContentTitle());
  }

  @Test
  public void deleteDocument() {
    Document document = createADocument();
    documentRepository.saveAndFlush(document);
    documentRepository.deleteById(document.getId());
    Optional<Document> foundDoc = documentRepository.findById(document.getId());
    assertTrue(foundDoc.isEmpty());
  }

  public Document createADocument() {
    Document document = new Document();
    document.setContentTitle("Boeing’s Starliner and SpaceX’s Starship have raised safety worries at NASA");
    document.setContentText(
        "NASA’s independent safety advisory group warned the space agency on Thursday (May 17) not to hurry into a crew test flight of Boeing’s troubled Starliner spacecraft, expressing worries regarding the final certification of the capsule’s parachutes and Boeing’s staffing numbers on the program.\n"
            + "SpaceX’s proposal to launch the massive Starship rocket from pad 39A at the KSC (Kennedy Space Center) facility, which is also utilized for crew flights to the ISS (International Space Station), has also raised “clear safety issues,” according to the safety consultants.\n"
            + "Next week, Boeing plans to retry a troubled Starliner test flight. The Orbital Flight Test-2 (OFT-2) mission will not transport astronauts. However, if the OFT-2 mission goes well, it would open the way for the subsequent Starliner launch, which will transport personnel to the space station for the last demonstration trip known as the CFT (Crew Flight Test), before Boeing and NASA deem the new commercial aircraft operational.\n"
            + "Along with SpaceX’s Dragon spaceship, which debuted with personnel for the first moment in May 2020, NASA will have a second human-rated capsule able to ferry passengers to and from space station thanks to public-private cooperation.\n"
            + "NASA officials have time to work out technical difficulties with the Starliner spaceship now that SpaceX is offering frequent crew transportation facilities to the space station. Nonetheless, NASA is determined to have 2 crew transportation carriers so that it will not have to rely on Russia’s Soyuz spaceship for astronaut trips if SpaceX has any substantial delays.\n"
            + "“From all indications, there is no need to rush to CFT,” David West, who is an Aerospace Safety Advisory Panel member, said in a public hearing on Thursday. “NASA has continuously stated that the program will move to CFT when and only when they are ready.  Of course, a successful OFT-2 is the most direct road to CFT.”\n"
            + "Since 2010, NASA has secured contracts with Boeing for the Starliner development, operations, and test flights totaling more than $5 billion. Following the conclusion of the OFT-2 trip and the Crew Flight Test that has a shorter duration, involving astronauts on board, the deals include contracts for six crew rotation trips to the space station, each with a 4-person crew.\n"
            + "The Starliner program, on the other hand, has been stalled for years. Because of software issues, the OFT-1 mission was unable to dock with the space station in 2019, prompting Boeing to fund a second uncrewed test flight. Last August, engineers observed that 13 oxidizer isolation valves that are in the Starliner spacecraft’s propulsion system were locked in the closed position, preventing the mission from taking off on top of a ULA (United Launch Alliance) Atlas 5 rocket.\n"
            + "Boeing delivered the Starliner spaceship back to ULA’s rocket hangar on May 4 after 9 months of investigations, testing, and a switch to a new propulsion module, in preparation for another launch attempt.\n"
            + "“There is some uncertainty as to if a valve redesign will be needed for future flights after OFT-2,” West said on Thursday, adding that NASA managers have bowed out on the oxidizer valve modification for the OFT-2 mission. Managers also accepted “flight reasoning” for problems with a high-pressure latch valve that is on the Starliner command module’s propulsion system, which is unrelated to the oxidizer valves that are in the service module, he said.");
    document.setContentUrl(
        "https://mbutimeline.com/space/128/boeings-starliner-and-spacexs-starship-have-raised-safety-worries-at-nasa/");
    document.setLanguageCode("en");
    document.setPublishedDate(OffsetDateTime.of(2022, 05, 20, 1, 1, 1, 0, ZoneOffset.UTC));
    document.setSourceName("mbu timeline");
    return document;
  }
}
