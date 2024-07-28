package com.thinksync.security.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedExceptionHandler implements AccessDeniedHandler {

  /**
   * @param request               that resulted in an <code>AccessDeniedException</code>
   * @param response              so that the user agent can be advised of the failure
   * @param accessDeniedException that caused the invocation
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    LocalDateTime currentTimeStamp = LocalDateTime.now();
    String message = (accessDeniedException != null && accessDeniedException.getMessage() != null)?accessDeniedException.getMessage():"Unauthorized";
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
