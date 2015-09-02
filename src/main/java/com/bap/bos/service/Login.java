package com.bap.bos.service;

import java.util.List;

import com.bap.authority.util.RoleFunction;
import com.bap.bos.domain.Staff;

public interface Login {

	public List<RoleFunction> Login_function(String admin, String pas);
	
	public  List<Staff> adminCheck(String admin, String pas);

}