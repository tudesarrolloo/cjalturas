package com.cjalturas.security;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cjalturas.dataaccess.dao.IEconomicsectorDAO;
import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.exceptions.ZMessManager.GettingException;
import com.cjalturas.model.Economicsector;
import com.cjalturas.model.control.IEconomicsectorLogic;
import com.cjalturas.model.dto.EconomicsectorDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura www.zathuracode.org
 *
 */
@Scope("singleton")
@Component("zathuraCodeAuthenticationProvider")
public class ZathuraCodeAuthenticationProvider implements AuthenticationProvider {
  
//  @Autowired
//  private IEconomicsectorLogic economicsectorLogic;
  
//  @Autowired
//  private IEconomicsectorDAO economicsectorDAO;
  
  /**
   * Security Implementation
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String name = authentication.getName();
    String password = authentication.getCredentials().toString();
    
    extracted();

    if (name.equals("admin") && password.equals("admin")) {
      final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
      grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

      final UserDetails principal = new User(name, password, grantedAuths);
      final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);

      return auth;
    } else {
      return null;
    }
  }
  
  @Transactional(readOnly = true)
  private void extracted() {
//    List<Economicsector> economicSectors;
//    final String uri = "http://localhost:8080/springrestexample/employees.json";
//
//    RestTemplate restTemplate = new RestTemplate();
//    String result = restTemplate.getForObject(uri, String.class);
//
//    System.out.println(result);
    

//    List<Economicsector> list = new ArrayList<Economicsector>();
//
//    try {
//      list = economicsectorDAO.findAll();
//    } catch (Exception e) {
//      e.printStackTrace();
//    } 
    
    try {
      List<EconomicsectorDTO> es = economicsectorLogic.getDataEconomicsector();
      System.out.println(es.size());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    

  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
