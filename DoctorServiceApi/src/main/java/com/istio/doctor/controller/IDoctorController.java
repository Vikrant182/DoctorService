package com.istio.doctor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.istio.doctor.response.model.DocRespModel;

/********************************************************************
 * 
 * 
 * @author saket
 *
 * This Interface is an abstraction layer of getting
 * doctor information based on specific criterias by 
 * exposing endpoints on doctor noun
 ********************************************************************/
@RequestMapping("/doctor")
public interface IDoctorController {
	
	/********************************************
	 * Gets all the doctor information registered 
	 * within the system
	 * @return
	 *********************************************/
	@GetMapping("/get-info/all")
	public ResponseEntity<List<DocRespModel>> getAllDocsInfo();
	
	/********************************************
	 * Gets one doctor information registered 
	 * within the system based on doctor id
	 * @return
	 *********************************************/
	
	@GetMapping("/get-info/id/{docid}")
	public ResponseEntity<DocRespModel> getDocsInfoByDocId(@PathVariable("docid") int docId);
	
	/********************************************
	 * Gets one doctor information registered 
	 * within the system based on doctor name
	 * @return
	 *********************************************/
	
	@GetMapping("/get-info/name/{docname}")
	public ResponseEntity<DocRespModel> getDocsInfoByDocName(@PathVariable("docname") String docName);

}
