package com.genpact.dao;

import com.genpact.pojo.Admin;

public interface AdminDAO {

	boolean checkAdmin(Admin admin);
	Admin getAdminDetail(String adminID);
}
