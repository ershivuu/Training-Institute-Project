package com.genpact.daoimpl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.genpact.dao.AdminDAO;
import com.genpact.pojo.Admin;

@Service
public class AdminDAOImpl implements AdminDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean checkAdmin(Admin admin) {
		
		boolean b = true;
		String query = "SELECT * FROM admin WHERE adminID = ? "
				+ "and adminPassword = ?";
		
		try {
			Admin ref = jdbcTemplate.queryForObject(query, 
						new BeanPropertyRowMapper<Admin>(
								Admin.class),
						new Object[] {admin.getAdminID() , 
								admin.getAdminPassword()});
			if(ref != null)
				return true;
			else
				return false;
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public Admin getAdminDetail(String adminID) {
		
		String query = "SELECT * FROM admin WHERE adminID = ?";
		
		try {
			Admin ref = jdbcTemplate.queryForObject(query, 
					new BeanPropertyRowMapper<Admin>(Admin.class),
					new Object[] {adminID});
			
			return ref;
			
        } catch (Exception e) {
        	e.printStackTrace();
        	return new Admin();
        }
	}
}











