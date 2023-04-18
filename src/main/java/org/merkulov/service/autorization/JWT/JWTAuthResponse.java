package org.merkulov.service.autorization.JWT;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class JWTAuthResponse {
   private String accessToken;
   private String tokenType = "Bearer ";
}
