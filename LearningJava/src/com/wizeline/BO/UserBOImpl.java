package com.wizeline.BO;

import java.util.logging.Logger;

import com.wizeline.DAO.UserDAO;
import com.wizeline.DAO.UserDAOImpl;
import com.wizeline.DTO.ErrorDTO;
import com.wizeline.DTO.ResponseDTO;
import com.wizeline.utils.Utils;

public class UserBOImpl implements UserBO {

	private static final Logger LOGGER = Logger.getLogger(UserBOImpl.class.getName());

	@Override
	public ResponseDTO createUser(String user, String password) {
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "fail";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserDAO userDAO = new UserDAOImpl();
			result = userDAO.createUser(user, password);
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("OK000");
			response.setStatus(result);
			response.setErrors(new ErrorDTO("ER001", "Error al crear usuario"));
		}
		return response;
	}

	@Override
	public ResponseDTO login(String user, String password) {
		LOGGER.info("Inicia procesamoento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserDAO userDAO = new UserDAOImpl();
			result = userDAO.login(user, password);
		}
		if ("success".equals(result)) {
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("ER001");
			response.setStatus("fail");
			response.setErrors(new ErrorDTO("ER001", result));
		}
		return response;
	}
}
