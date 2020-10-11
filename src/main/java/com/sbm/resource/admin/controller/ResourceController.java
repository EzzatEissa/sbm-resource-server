package com.sbm.resource.admin.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class ResourceController {

	@RequestMapping("/")
	public String hello() {
		return "un secured";
	}

	@RequestMapping("/protected")
	public String protectedHello(Principal principal, HttpServletRequest request) {
//    	Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl)
//                .grantType(OAuth2Constants.PASSWORD).realm("master").clientId("admin-cli")
//                .username("admin").password("Testing123")
//                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();

//        keycloak.tokenManager().getAccessToken();
		request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//    	AccessToken token = ((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken();
//		AccessToken token = ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();
		KeycloakAuthenticationToken token2 = (KeycloakAuthenticationToken) request.getUserPrincipal();
		KeycloakPrincipal principal2 = (KeycloakPrincipal) token2.getPrincipal();
		KeycloakSecurityContext session = principal2.getKeycloakSecurityContext();
		AccessToken token = session.getToken();

//        username = token.getPreferredUsername();
//        emailID = token.getEmail();
//        lastname = token.getFamilyName();
//        firstname = token.getGivenName();
//        realmName = token.getIssuer();            
//        Access realmAccess = token.getRealmAccess();
//        roles = realmAccess.getRoles();

		return "i was protected userId: " + principal.getName() + " my scope: " + token.getScope();
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin is protected";
	}

}