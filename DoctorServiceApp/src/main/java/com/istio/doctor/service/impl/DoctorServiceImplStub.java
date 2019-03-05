package com.istio.doctor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.istio.doctor.aspect.Loggable;
import com.istio.doctor.exception.DocNotFoundException;
import com.istio.doctor.response.model.DocRespModel;
import com.istio.doctor.service.IDoctorService;

import lombok.extern.slf4j.Slf4j;

/***********************************************
 * @author saket
 * This is a stubbed class and returns
 * stubbed response to the user for doctor noun
 ***********************************************/
@Qualifier("stubbed")
@Slf4j
@Service
@Loggable
public class DoctorServiceImplStub implements IDoctorService {
	
	List<DocRespModel> docModelList = new ArrayList<DocRespModel>();
	DoctorServiceImplStub()
	{
		
		log.info("stubbing doctor info ....");
		for(int i=0;i<3;i++)
		{
			DocRespModel model = new DocRespModel();
			model.setId(i);
			model.setAddress("strausse"+i);
			model.setName("Name"+i);
			model.setContactNo("99999"+i);
			docModelList.add(model);
		}
		
	}

	/**********************************************
	 * Returns list of doctor info stubbed response
	 **********************************************/
	public List<DocRespModel> findAllDocsInfo() {
		return docModelList;
	}
	
	/**********************************************
	 * Returns one doctor info stubbed response
	 * based on doctorid
	 **********************************************/
	public DocRespModel findDocsInfoByDocId(int docId) {
		DocRespModel model = new DocRespModel();
		docModelList.forEach(docModel->{
			
			if(docModel.getId() == docId)
			{
				model.setId(docId);
				model.setAddress(docModel.getAddress());
				model.setContactNo(docModel.getContactNo());
				model.setName(docModel.getName());
			}
		});
		if(model==null || model.getAddress()==null)
		{
			throw new DocNotFoundException("Doctor not found for id::"+docId);
		}
		return model;
	}

	/**********************************************
	 * Returns one doctor info stubbed response
	 * based on doctor name
	 **********************************************/
	public DocRespModel findDocsInfoByDocName(String docName) {
		DocRespModel model = new DocRespModel();
		docModelList.forEach(docModel->{
			
			if(docModel.getName().equalsIgnoreCase(docName))
			{
				model.setId(model.getId());
				model.setAddress(docModel.getAddress());
				model.setContactNo(docModel.getContactNo());
				model.setName(docName);
			}
		});
		
		if(model==null || model.getAddress()==null)
		{
			throw new DocNotFoundException("Doctor not found for name::"+docName);
		}
		return model;
	}

}
