package com.istio.doctor.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.istio.doctor.aspect.Loggable;
import com.istio.doctor.controller.IDoctorController;
import com.istio.doctor.response.model.DocRespModel;
import com.istio.doctor.service.IDoctorService;

/************************************************
 * Implementing class for IDoctorController.Java
 * @author saket
 ************************************************/
@RestController
@Loggable
public class DoctorControllerImpl implements IDoctorController {

	
	@Autowired
	@Qualifier("stubbed")
	IDoctorService doctorService;
	/**************************************************************
	 * Returns list of Doctors information registered with  system
	 **************************************************************/
	public ResponseEntity<List<DocRespModel>> getAllDocsInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.findAllDocsInfo());
	}

	/**************************************************************
	 * Returns one Doctor information based on doctor id
	 **************************************************************/
	public ResponseEntity<DocRespModel> getDocsInfoByDocId(int docId) {
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.findDocsInfoByDocId(docId));
	}

	/**************************************************************
	 * Returns one Doctor information based on doctor name
	 **************************************************************/
	public ResponseEntity<DocRespModel> getDocsInfoByDocName(String docName) {
		 return ResponseEntity.status(HttpStatus.OK).body(doctorService.findDocsInfoByDocName(docName));
	}

}
