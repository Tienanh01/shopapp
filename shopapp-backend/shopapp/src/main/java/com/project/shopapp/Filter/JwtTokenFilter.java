package com.project.shopapp.Filter;

import com.project.shopapp.components.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    // this funtion will bi check how access and how deny
    @Value("${api.prefix}")
    private String apiPrefix ;
    @Autowired
    private JwtTokenUtil jwtTokenUtil ;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,@NotNull  FilterChain filterChain) throws ServletException, IOException {

     try {
         if(isByPassToken(request) ){
             filterChain.doFilter(request,response);// enable by pass
         }
         final String authHeader = request.getHeader("Authorization");
         if(authHeader != null &&  authHeader.startsWith("Bearer ")){
             final String token  = authHeader.substring(7);
             final String phoneNumber =  jwtTokenUtil.extractPhoneNumber(token);
             if(phoneNumber != null  && SecurityContextHolder.getContext().getAuthentication() == null){
                 UserDetails userDetails = userDetailsService.loadUserByUsername(phoneNumber);
                 if(jwtTokenUtil.validateToken(token , userDetails)){
                     UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                             userDetails , null, userDetails.getAuthorities()
                     );
                     authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                     SecurityContextHolder.getContext().setAuthentication(authenticationToken);


                 }

             }
             filterChain.doFilter(request,response) ;

         }
     }catch (Exception e ){
         response.sendError(HttpServletResponse.SC_UNAUTHORIZED , "Unauthorized");
         return;
     }


//        filterChain.doFilter(request, response);  // access all request
    }

    private boolean isByPassToken (@NotNull HttpServletRequest request){

        final List<Pair<String, String>> bypassTokens = Arrays.asList(

                Pair.of(String.format("%s/products",apiPrefix),"GET"),
                Pair.of(String.format("%s/categories",apiPrefix),"GET"),
                Pair.of(String.format("%s/users/register",apiPrefix),"POST"),
                Pair.of(String.format("%s/users/login",apiPrefix),"POST")
        );
        for(Pair<String , String > bypassToken : bypassTokens ){
            if(request.getServletPath().contains(bypassToken.getFirst()) && request.getMethod().equals(bypassToken.getSecond() ) ){
               return true ;
            }
        }
        return  false;
    }

     // question , lam sao de  bat token truoc tat ca cac rquest
    // o SecurityFilterChang  then . addFilterBefore

}
