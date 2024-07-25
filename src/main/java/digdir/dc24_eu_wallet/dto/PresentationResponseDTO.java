package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

/**
 * The response from Mattr, that contains the QR-CODE
 * String.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Setter
@Getter
public class PresentationResponseDTO {
  @Expose
  private String id;
  @Expose
  private String callbackUrl;
  @Expose
  private String expiresTime;
  @Expose
  private String didcommUri;
}
