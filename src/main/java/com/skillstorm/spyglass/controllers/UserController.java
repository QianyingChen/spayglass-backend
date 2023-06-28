package com.skillstorm.spyglass.controllers;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@CrossOrigin(allowCredentials = "true", originPatterns = "http://localhost:5173")
public class UserController {

	@Autowired
	private OAuth2AuthorizedClientService clientService;
	

	@GetMapping("/signin")
	public RedirectView redirectView() {
		RedirectView redirectView = new RedirectView("http://localhost:5173");
		return redirectView;
	}
	
	
	@GetMapping("/userinfo")
	@ResponseBody // Send the data as JSON
	public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User user) {
		// Info about the user
		System.out.println("Username:"+user.getAttribute("name"));
		System.out.println("Email Address:"+user.getAttribute("email"));
		return user.getAttributes();

	}

	
//	@GetMapping("/signout")
//    public RedirectView logout(HttpServletRequest request) throws ServletException {
//        // Invalidate the session
//        request.logout();
//
//        // Revoke the OAuth2 access token
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof OAuth2AuthenticationToken) {
//            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
//                oauthToken.getAuthorizedClientRegistrationId(),
//                oauthToken.getName()
//            );
//            if (client != null) {
//                clientService.removeAuthorizedClient(
//                    oauthToken.getAuthorizedClientRegistrationId(),
//                    oauthToken.getName()
//                );
//            }
//        }
//
//        RedirectView redirectView = new RedirectView("http://localhost:5173/logout-success");
//        return redirectView;
//    }
	
	
}