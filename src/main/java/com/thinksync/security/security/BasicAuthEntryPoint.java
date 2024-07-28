package com.thinksync.security.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class BasicAuthEntryPoint implements AuthenticationEntryPoint {

  /**
   * @param request       that resulted in an <code>AuthenticationException</code>
   * @param response      so that the user agent can begin authentication
   * @param authException that caused the invocation
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    LocalDateTime currentTimeStamp = LocalDateTime.now();
    String message = (authException != null && authException.getMessage() != null)?authException.getMessage():"Unauthorized";
    response.setHeader("security-error-reason",message);
    // response.sendError(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase());   // This method sends an error response to the client using the specified status code and a custom message.

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType("application/json;charset=UTF-8");

    String formattedMessage = "Timestamp: " + currentTimeStamp + "\n"
        + "Status: " + HttpStatus.UNAUTHORIZED.value() + "\n"
        + "Exception: " + HttpStatus.UNAUTHORIZED.getReasonPhrase() + "\n"
        + "Message: " + message + "\n"
        + "Path: " + request.getRequestURI() + "\n";

    response.getWriter().write(formattedMessage);
  }
}
