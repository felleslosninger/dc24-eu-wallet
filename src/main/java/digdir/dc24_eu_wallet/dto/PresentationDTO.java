package digdir.dc24_eu_wallet.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Hold Presentation data witch we get back from
 * Mattr API callback.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Setter
@Getter
public class PresentationDTO {
  private String presentationType;
  private String challengeId;
  private Map<String, Object> claims;
  private boolean verified;
  private String holder;
  private Presentation presentation;

  /**
   * Represents a presentation, which includes context, type, holder, and proofs.
   */
  @Setter
  @Getter
  public static class Presentation {
    private List<String> context;
    private List<String> type;
    private String holder;
    private List<Proof> proof;
    }

  /**
   * Represents a proof associated with a presentation or credential.
   */
  @Setter
  @Getter
  public static class Proof {
    private String type;
    private String created;
    private String verificationMethod;
    private String proofPurpose;
    private String challenge;
    private String domain;
    private String jws;
  }
}