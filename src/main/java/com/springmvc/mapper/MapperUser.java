package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.springmvc.model.UserDetailsCustom;

public class MapperUser implements RowMapper<UserDetailsCustom> {
	
	private Collection<GrantedAuthority> getUserAuthorities(String role) {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
		
		return authorities;
	}

    @Override
    public UserDetailsCustom mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UserDetailsCustom user = new UserDetailsCustom(rs.getString("username"),
				    								   rs.getString("password"),
				    								   getUserAuthorities(rs.getString("role")));
    	user.setId(rs.getInt("id"));
    	
    	user.setEmail(rs.getString("email"));
    	user.setAddress(rs.getString("address"));
    	user.setFullname(rs.getString("fullname"));
    	user.setRole(rs.getString("role"));
    	user.setToken(rs.getString("token"));
    	user.setPhone(rs.getString("phone"));
    	
    	user.setCreatedDate(rs.getTimestamp("createdDate"));
    	user.setCreatedBy(rs.getString("createdBy"));
    	user.setModifiedDate(rs.getTimestamp("modifiedDate"));
    	user.setModifiedBy(rs.getString("modifiedBy"));
        
        return user;
    }
}