package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.springmvc.model.Language;

public class MapperLanguage implements RowMapper<Language> {

    @Override
    public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
        Language language = new Language();
        
        language.setId(rs.getInt("id"));
        
        language.setName(rs.getString("name"));
        language.setCode(rs.getString("code"));
        
        language.setCreatedDate(rs.getTimestamp("createdDate"));
        language.setModifiedDate(rs.getTimestamp("modifiedDate"));
        language.setCreatedBy(rs.getString("createdBy"));
        language.setModifiedBy(rs.getString("modifiedBy"));
        
        return language;
    }
}