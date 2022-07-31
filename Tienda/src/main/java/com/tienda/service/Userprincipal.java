
package com.tienda.service;

import com.tienda.entity.Persona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author David
 */

// El UserDetails nos permite guardar la informacion del usuario como tal pero no se usa para Spring Security.
public class Userprincipal implements UserDetails {
    private Persona persona; 
    
    public Userprincipal (Persona persona){
        this.persona = persona; 
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
       /*  Se crea una lista de tipo GrantedAuthority que es un permiso que le
         permite al usuario lo que puede o no hacer. */
      List<GrantedAuthority> authorities = new ArrayList<>();
      
      
      // Extract list of permmisions (name)
      
      this.persona.getPermissionList().forEach(p -> {
          GrantedAuthority authority = new SimpleGrantedAuthority(p); 
          authorities.add(authority); 
      });
      
      // Extract list of roles (ROLE_name)
      /* El permiso GrantedAuthorty solo permite que se le ponga ROLE_ para
      identificar si es un role como tal. */
       this.persona.getRoleList().forEach(r -> {
          GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r); 
          authorities.add(authority); 
      });
      return authorities; 
    }

    @Override
    public String getPassword() {
      return this.persona.getPassword();
    }

    @Override
    public String getUsername() {
      return this.persona.getNombre(); 
    }

    @Override
    public boolean isAccountNonExpired() {
       return true; 
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; 
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true; 
    }
    
    // Para saber si esta activo o inactivo
    @Override
    public boolean isEnabled() {
        return this.persona.getActive()==1;  
    }
}


