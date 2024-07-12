package com.E_Bank.bank.Security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.security.Key;


public class JwtAuthorizationFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationToken=request.getHeader("Authorization");
        if(authorizationToken!=null && authorizationToken.startsWith("Bearer ")){
            try {
                String jwt=authorizationToken.substring(7);
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtAuth.SECRET_KEY)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                // Vous pouvez maintenant utiliser les claims pour obtenir des informations du JWT
                 String username = claims.getSubject();
         ;
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request,response);

            }catch(Exception e){
                response.setHeader("error message",e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);

            }
        }else {
            filterChain.doFilter(request,response);
        }
    }
}
