package Ampersand.GKR.global.filter;

import Ampersand.GKR.global.error.exception.ErrorCode;
import Ampersand.GKR.global.error.exception.ErrorMessage;
import Ampersand.GKR.global.error.exception.GkrException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static Ampersand.GKR.global.error.exception.ErrorCode.*;

@Component
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

    public void setErrorResponse(ErrorCode errorCode, HttpServletResponse response) throws IOException {
        response.setStatus(errorCode.getStatus());
        response.setContentType("application/json; charset=utf-8");

        ErrorMessage errorMessage = new ErrorMessage(errorCode);

        String errorResponseEntityToJson = objectMapper.writeValueAsString(errorMessage);
        response.getWriter().write(errorResponseEntityToJson);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 ExpiredJwtException 발생 ===================");
            setErrorResponse(TOKEN_IS_EXPIRED, response);
        } catch (JwtException | GkrException ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 TokenNotValidException 발생 ===================");
            setErrorResponse(TOKEN_NOT_VALID, response);
        } catch (Exception ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 Exception 발생 ===================");
            setErrorResponse(UNKNOWN_ERROR, response);
            ex.printStackTrace();
        }
    }
}
