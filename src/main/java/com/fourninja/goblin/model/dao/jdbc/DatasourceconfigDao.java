package com.fourninja.goblin.model.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fourninja.goblin.config.model.entity.Datasourceconfig;

@Component
public class DatasourceconfigDao {

	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DatasourceconfigDao(DataSource dataSource){
		this.dataSource=dataSource;
	}
	
	public List<Datasourceconfig> getAll(){
		jdbcTemplate = new JdbcTemplate(dataSource);
		RowMapper<Datasourceconfig> rowMapper = (rs, rowNum) -> {
			Datasourceconfig cfg=new Datasourceconfig();
			cfg.setId(rs.getInt("id"));
			cfg.setUsername(rs.getString("username"));
			cfg.setPassword(rs.getString("password"));
			cfg.setName(rs.getString("name"));
			cfg.setUrl(rs.getString("url"));
			cfg.setDriverclassname(rs.getString("driverclassname"));
			return cfg;
		    
		};
		List<Datasourceconfig> list=jdbcTemplate.query("select * from datasourceconfig", new Object[]{}, rowMapper);
		return list;
	}
}
