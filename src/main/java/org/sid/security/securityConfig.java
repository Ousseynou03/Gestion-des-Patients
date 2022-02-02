package org.sid.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration /* Les classes avec cette annotation sont généralement des classes 
qui vont être traitées au démarrage de l'application */

public class securityConfig extends WebSecurityConfigurerAdapter {
	/*elle hérite de la classe WebSecutityConfigurerAdapter
   *	qui est fournie par springSecurity
   */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");// Stratégie de stokage des utilisateurs
		auth.inMemoryAuthentication().withUser("user2").password("{noop}1234").roles("USER");
		auth.inMemoryAuthentication().withUser("user3").password("{noop}1234").roles("USER","ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// A partir de là, on va spécifier les actions qui sont autorisées
		 http.formLogin();
		 
		// On veut dire à springSecurity qu'on veut utiliser un formulaire de connexion
		//http.httpBasic(); c'est le navigateur web qui va se charger d'afficher 
		
		 http.authorizeRequests().anyRequest().authenticated() ;
		  
	}

}
