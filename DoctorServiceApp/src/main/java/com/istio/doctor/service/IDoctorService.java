package com.istio.doctor.service;

import java.util.List;

import com.istio.doctor.response.model.DocRespModel;

/*****************************************************************************
 * @author saket
 *
 *Abstarction of the service layer. Any service implementation on doctor noun
 *should implement this interface. If a new interface is written on doctor noun
 *it should extend this interface
 ******************************************************************************/
public interface IDoctorService {
	
	/**
	 * 
	 * @return
	 */
	public List<DocRespModel> findAllDocsInfo();

	/**
	 * 
	 * @param docid
	 * @return
	 */
	public DocRespModel findDocsInfoByDocId(int docId);
	
	/**
	 * 
	 * @param docname
	 * @return
	 */
	public DocRespModel findDocsInfoByDocName(String docName);
}
