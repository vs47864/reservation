package de.fraunhofer.iosb.seucrity;

import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationTokenFilter.class);
    private final String tokenHeader = "Authorization";

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private LoginService loginService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("in doFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        LOG.debug("requestURI: {}", httpRequest.getRequestURI());
        String authToken = httpRequest.getHeader(tokenHeader);
        LOG.debug("authToken: {}", authToken);
        String username = tokenUtils.getUsernameFromToken(authToken);

        if (username != null) { // && SecurityContextHolder.getContext().getAuthentication() == null) {
            LOG.debug("found user by provided token - username: {}", username);
            User user = loginService.getUserFromToken(authToken);
            if (tokenUtils.validateToken(authToken)) {
                LOG.debug("user '{}' has valid token", username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Token not valid
                throw new UnauthorizedException();
            }
        }

        chain.doFilter(request, response);
    }
}

