package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

/**
 * This class contains the data which is sent
 * to Mattr to create the QR code.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Setter
@Getter
public class PresentationRequestDTO {
  @Expose
  private String challenge;
  @Expose
  private String did;
  @Expose
  private String templateId;
  @Expose
  private String callbackUrl;
}