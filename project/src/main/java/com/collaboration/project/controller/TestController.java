package com.collaboration.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.result.Output;
import org.hibernate.type.ClobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.collaboration.project.dao.JobDao;
import com.collaboration.project.dao.UsersDao;
import com.collaboration.project.model.Job;
import com.collaboration.project.model.MyError;
import com.collaboration.project.model.Users;
import com.collaboration.project.service.MailServices;

import oracle.jdbc.OracleConnection;
import oracle.sql.CLOB;

@RestController
public class TestController {
	@Autowired
	UsersDao usersDao;
	@Autowired
	JobDao jobDao;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	HttpServletRequest request;
	@Autowired
	MailServices mailServices;
	
	@RequestMapping("/allusers")
	public ResponseEntity<List<Users>> getAllusers()
	{
		System.out.println(usersDao.getAll());
		return new ResponseEntity<List<Users>>(usersDao.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/adduser",method=RequestMethod.POST)
	public  ResponseEntity<Map<String,String>> adduser(@RequestBody @Valid Users users,BindingResult result)
	{
		Map<String,String> errorMap=new HashMap<String,String>();
		if(result.hasErrors())
		{
			System.out.println("error occured");
			
			
		for(FieldError error:result.getFieldErrors())
		{
		  
		  errorMap.put(error.getField(),error.getDefaultMessage());
		}
			return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.PARTIAL_CONTENT);
		}
		boolean b=usersDao.createUser(users);
		//users.setResponseMessage("You have successfully posted the data to server!");
		if(b)
		{
			Users user=usersDao.getUserByEmail(users.getEmail());
			mailServices.sendMail(users.getEmail(),user.getUserId());
		}
		return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Users> login(@RequestBody Users users)
	{
		boolean result=usersDao.login(users);
		System.out.println("result values:::::::::::::::"+result);
		System.out.println(result);
		if(result)
		{users=usersDao.getUserByEmail(users.getEmail());
		    if(users.isVerified())
		    {
			return new ResponseEntity<Users>(users,HttpStatus.OK);
		    }else
		    {
		    	return new ResponseEntity<Users>(users,HttpStatus.NOT_MODIFIED);
		    }
		}else
		{
			MyError er = new MyError(1,"Authentication failed");
			// am adding
			return new ResponseEntity<Users>(users,HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/newjob")
	public ResponseEntity<List<Job>> newJob(@RequestParam("companyLogo") MultipartFile logo,@RequestParam("companyName") String companyName,@RequestParam("companyJobId")Integer companyJobId,@RequestParam("jobTitle") String jobTitle,@RequestParam("jobDescription") String jobDescription,@RequestParam("qualification") String qualification,@RequestParam("salary") Integer salary,@RequestParam("interviewDate") Date interviewDate ) throws IOException, SQLException
	
	{
		try{
		System.out.println(logo);
		Job job=new Job();
		job.setCompanyName(companyName);
		job.setCompanyJobId(companyJobId);
		job.setJobTitle(jobTitle);
		job.setJobDescription(jobDescription);
		job.setQualification(qualification);
		job.setSalary(salary);
		job.setInterviewDate(interviewDate);
	    jobDao.postJob(job);
	    
	   InputStream inputStream= logo.getInputStream();
	   byte[] data=new byte[inputStream.available()];
	   inputStream.read(data);
	   
	   File file=new File(request.getRealPath("//")+"//jobs//comapanylogo//");
	   if(!file.exists())
	   {
		   file.mkdirs();
	   }
	   //System.out.println(request.getRealPath("//"));
	   System.out.println(file.getAbsolutePath());
	   OutputStream outputStream=new FileOutputStream(file.getAbsolutePath()+"//"+companyName+".jpg");
	   outputStream.write(data);
	  
		
		
   
		return new ResponseEntity<List<Job>>(jobDao.getAllJobs(),HttpStatus.OK);
		}catch(Exception e)
		{
			System.out.println(e);
			return new ResponseEntity<List<Job>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/allJobs")
	public ResponseEntity<List<Job>> allJobs()
	{
		System.out.println("hello all jobs");
		return new ResponseEntity<List<Job>>(jobDao.getAllJobs(),HttpStatus.OK);
	}
	
	@RequestMapping("/jobError")
	public ResponseEntity<Void> jobError()
	{
		System.out.println("hello job error");
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	

}
