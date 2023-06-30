package com.skillstorm.spyglass.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

//@Configuration
//public class SecurityConfig {
//
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(requests -> requests
//                .anyRequest().authenticated())
//                .csrf(csrf -> csrf.disable())
//                .oauth2Login(withDefaults());              	
////        		.and()
////        		.logout(logout -> logout.permitAll()
////	                .logoutSuccessHandler((request, response, authentication) -> {
////	                    response.setStatus(HttpServletResponse.SC_OK);
////	                }));
//    	                         
//        
//        http.cors(cors -> cors.configurationSource(request -> {
//            CorsConfiguration corsConfig = new CorsConfiguration();
//            corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
//            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//            corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//            corsConfig.setAllowCredentials(true);
//            corsConfig.setMaxAge(3600L); // For an hour, cache the preflight cors request 
//                       
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", corsConfig);
//            return corsConfig;
//        }));
//		
//		return http.build();
//	}		
//}


@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .oauth2Login()
            .and()
            .logout(logout -> logout.permitAll()
            .logoutSuccessHandler((request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
        }));

        http.cors().configurationSource(request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();
            corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5173", 
            			"http://localhost:5173",
            			"http://localhost:8089",
            			"http://qianying-project3-pipeline-bucket.s3-website-us-east-1.amazonaws.com/"));
        
            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
            corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
            corsConfig.setAllowCredentials(true); // Allows cookies
            corsConfig.setMaxAge(3600L); // For an hour, cache the preflight cors request

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfig);
            return corsConfig; // Return the CorsConfiguration object
        });

        return http.build();
    }
}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .cors().and() // Enable CORS
//            .authorizeRequests(authorize -> authorize
//                .antMatchers("/").permitAll() // Allow public access to certain endpoints
//                .anyRequest().authenticated() // Require authentication for other endpoints
//            )
//            .oauth2Login(oauth2 -> oauth2
//                .defaultSuccessUrl("/callback") // Redirect URI after successful login
//            )
//            .logout(logout -> logout
//                .logoutSuccessUrl("/logout") // Redirect URI after successful logout
//            );
//
//        http.cors().configurationSource(request -> {
//            CorsConfiguration corsConfig = new CorsConfiguration();
//            corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
//            corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//            corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//            corsConfig.setAllowCredentials(true); // Allows cookies
//            corsConfig.setMaxAge(3600L); // For an hour, cache the preflight cors request
//
//            return corsConfig; // Return the CorsConfiguration object
//        });
//
//        return http.build();
//    }
//}




//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests(authorizeRequests -> authorizeRequests
//                .anyRequest().authenticated()
//            )
//            .csrf(csrf -> csrf.disable())
////            .oauth2Login(oauth2Login -> oauth2Login
////                .defaultSuccessUrl("/userinfo", true)
////            )
//            .oauth2Login()
//            .and()
//            .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessHandler((request, response, authentication) -> {
//                    response.setStatus(HttpServletResponse.SC_OK);
//                })
//            )
//            .cors(cors -> cors
//                .configurationSource(request -> {
//                    CorsConfiguration corsConfig = new CorsConfiguration();
//                    corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
//                    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//                    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//                    corsConfig.setAllowCredentials(true);
//                    corsConfig.setMaxAge(3600L);
//
//                    return corsConfig;
//                })
//            );
//
//        return http.build();
//    }
//}

