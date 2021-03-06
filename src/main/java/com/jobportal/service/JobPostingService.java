package com.jobportal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.exception.JobPostingNotFoundException;
import com.jobportal.model.Company;
import com.jobportal.model.Industry;
import com.jobportal.model.JobPosting;
import com.jobportal.model.Location;
import com.jobportal.repository.JobPostingRepository;
import com.jobportal.repository.LocationRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 * This service class of JobPosting serves as an intermediate layer between the DAO layer and the controller layer.
 * It is ideally used to enforce business rules and ensuring that the controllers only job is to prepare data and send it to the dao, 
 * while the daos layer only responsibility is to query the database and return data.
 * @author Darien Sosa
 */
@Service
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class JobPostingService {
	private JobPostingRepository jpRepo;
	
	public List<JobPosting> getAllJobPostings() {
		return jpRepo.findAll();
	}
	
	public List<JobPosting> getJobPostingsByTitle(String title) {
		return jpRepo.findByTitle(title);
	}
	
	public List<JobPosting> getJobPostingsByLocationId(Location locationId) {
		return jpRepo.findByLocationId(locationId);
	}
	
	public List<JobPosting> getJobPostingsByCompanyId(Company companyId) {
		return jpRepo.findByCompanyId(companyId);
	}
	
	public List<JobPosting> getJobPostingsByIndustryId(Industry industryId) {
		return jpRepo.findByIndustryId(industryId);
	}
	
	public JobPosting getJobPostingByPostingId(int id) {
		return jpRepo.findByPostingId(id);
	}
	
	public void insertJobPosting(JobPosting jp) {
		jpRepo.save(jp);
	}
	
	public void updateJobPosting(JobPosting jp) throws JobPostingNotFoundException {
		if (jpRepo.findById(jp.getPostingId()) != null) {
			jpRepo.save(jp);
		}
		else {
			throw new JobPostingNotFoundException ("Job posting could not be found.");
		}
	}
	
	public void deleteJobPosting(JobPosting jp) throws JobPostingNotFoundException {
		if (jpRepo.findById(jp.getPostingId()) != null) {
			jpRepo.delete(jp);
		}
		else {
			throw new JobPostingNotFoundException ("Job posting could not be found.");
		}
	}
	
	public JobPosting findByPrimaryKey(int id) throws JobPostingNotFoundException {
		if (jpRepo.findByPostingId (id) != null) {
			return jpRepo.findByPostingId(id);
		}
		else {
			throw new JobPostingNotFoundException ("Job posting could not be found.");
		}
	}

}
