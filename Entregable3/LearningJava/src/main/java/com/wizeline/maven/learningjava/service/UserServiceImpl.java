/**
 * 
 */
package com.wizeline.maven.learningjava.service;

import java.util.List;
import java.util.logging.Logger;

import com.wizeline.maven.learningjava.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wizeline.maven.learningjava.model.ErrorDTO;
import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.repository.UserRepositoryImpl;
import com.wizeline.maven.learningjava.utils.Utils;
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Override
	public ResponseDTO createUser(String user, String password) {
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "fail"; 
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserRepositoryImpl userDao = new UserRepositoryImpl();
			result = userDao.createUser(user, password);
			response.setCode("OK000");
			response.setStatus(result);
		}else {
			response.setCode("OK000");
			response.setStatus(result);
			response.setErrors(new ErrorDTO("ER001","Error al crear usuario"));
		}
		return response;
	}

	@Override
	public ResponseDTO login(String user, String password) {
		LOGGER.info("Inicia procesamiento en capa de negocio");
		ResponseDTO response = new ResponseDTO();
		String result = "";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserRepositoryImpl userDao = new UserRepositoryImpl();
			result = userDao.login(user, password);
		}
		if("success".equals(result)) {
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("ER001");
			response.setErrors(new ErrorDTO("ER001",result));
			response.setStatus("fail");
		}
		return response;
	}

	@Override
	public ResponseDTO eliminarUsuario(String user, String password) {
		ResponseDTO response = new ResponseDTO();
		String result = "";
		if (Utils.validateNullValue(user) && Utils.validateNullValue(password)) {
			UserRepositoryImpl userDao = new UserRepositoryImpl();
			result = userDao.deleteUser(user, password);
		}
		if("success".equals(result)) {
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("ER001");
			response.setErrors(new ErrorDTO("ER001",result));
			response.setStatus("fail");
		}
		return response;
	}

	@Override
	public ResponseDTO actualizarUsuario(List<UserDTO> userDTOList) {
		ResponseDTO response = new ResponseDTO();
		String result = "";
		if (Utils.validateNullValue(userDTOList.get(0).getUser()) && Utils.validateNullValue(userDTOList.get(0).getPassword()) && Utils.validateNullValue(userDTOList.get(1).getUser()) && Utils.validateNullValue(userDTOList.get(1).getPassword())) {
			UserRepositoryImpl userDao = new UserRepositoryImpl();
			result = userDao.updateUser(userDTOList.get(0).getUser(), userDTOList.get(0).getPassword(), userDTOList.get(1).getUser(), userDTOList.get(1).getPassword());
		}
		if("success".equals(result)) {
			response.setCode("OK000");
			response.setStatus(result);
		} else {
			response.setCode("ER001");
			response.setErrors(new ErrorDTO("ER001",result));
			response.setStatus("fail");
		}
		return response;
	}
}
