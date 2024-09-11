package com.example.attivita.security;

import com.example.attivita.exceptions.UnauthorizedException;
import com.example.attivita.user.User;
import com.example.attivita.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 0. Questo metodo verrà eseguito per ogni request che richieda di essere autenticati
        // 1. Verifichiamo se c'è l'header Authorization e estraiamo il token da esso
        String authHeader = request.getHeader("Authorization"); // authHeader --> Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjk5ODczNTI3LCJleHAiOjE3MDA0NzgzMjd9.bCJaensC-bddAiDfU6Jt6JNN8Wooo6lEzypQkylEnUY
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Per favore passa il Bearer Token nell'Authorization header");
        } else {
            String token = authHeader.substring(7);
            // 2. Verifico che il token non sia nè scaduto nè sia stato manipolato
                jwtTools.verifyToken(token);

            // 3. Se è tutto OK
            // 3.1 Cerco l'utente nel database tramite id (l'id sta nel payload del token, quindi devo estrarlo da lì)
                String id = jwtTools.extractIdFromToken(token);
                User currentUser = userService.findById(Integer.parseInt(id));
            // 3.2 Segnalo a Spring Security che l'utente ha il permesso di procedere
            // Se non faccio questa procedura, verrà comunque tornato 403
//                System.out.println(currentUser);

               Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(authentication);

            // 3.3 Procedo (vuol dire andare al prossimo blocco della filter chain)
            // 4. Se non è OK -> 401

            filterChain.doFilter(request, response);
        }

    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Questo metodo serve per specificare quando il filtro JWTAuthFilter non debba entrare in azione
        // Ad es tutte le richieste al controller /auth/** non devono essere filtrate
        String pathWithArguments = request.getServletPath() + request.getQueryString();

        List<String> excludedPaths = Arrays.asList("/auth");

        return excludedPaths.stream().anyMatch(pathWithArguments::startsWith);    }
}
