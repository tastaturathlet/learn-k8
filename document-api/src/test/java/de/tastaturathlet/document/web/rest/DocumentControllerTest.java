package de.tastaturathlet.document.web.rest;

import de.tastaturathlet.document.domain.Document;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//@WebAppConfiguration
//@ContextConfiguration(classes = {DocumentController.class, JpaAuditingConfig.class})
//@WebMvcTest
//@EnableJpaRepositories(basePackages = {"de.tastaturathlet.document.*"})
//@EntityScan("de.tastaturathlet.document.domain")
@Disabled
public class DocumentControllerTest {

/*  @Autowired
  private DocumentController documentController;

  @Autowired
  private MockMvc mockMvc;*/

  @BeforeEach
  public void cleanupEach() {
//    when(documentRepositoryMock.findAll()).thenReturn(new ArrayList<>());
//    when(documentRepositoryMock.findById(any())).thenReturn(Optional.of(new Document()));
//    when(documentRepositoryMock.save(any())).thenReturn(new Document());
//    doNothing().when(documentRepositoryMock.delete(any()));
  }

  @Test
  public void getDocumentById() throws Exception {

  /*  MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .get("/employees/{id}", 1)
            .accept(MediaType.APPLICATION_JSON))
//        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)).andReturn();

    assertNotNull(mvcResult);*/

  }

  @Test
  public void getAllDocument() {

  }

  @Test
  public void createDocument() {

  }

  @Test
  public void updateDocument() {

  }

  @Test
  public void deleteDocument() {

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
