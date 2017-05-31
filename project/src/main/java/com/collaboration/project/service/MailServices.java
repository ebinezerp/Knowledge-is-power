package com.collaboration.project.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.collaboration.project.model.Blog;
import com.collaboration.project.model.Users;

@Service
public class MailServices {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendMail(String email,Integer id)
	{
		MimeMessagePreparator mimeMessagePreparator=new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
				mimeMessage.setFrom(new InternetAddress("ebinezer.p87@gmail.com"));
				mimeMessage.setSubject("Verification mail");
				mimeMessage.setText(htmlHead()+"<p>Welcome to <H3>KNOWLEDGE Is POWER,</H3> forum.</p><p>Thanks for Registering with us. This is a verification mail, click the below link to verify your register mail.</P>"
						+ " <a href='http://127.0.0.1:8887/#/verification/"+id+"'><button style='padding:10px;border-radius:5px;color:white;background-color:#00BFFF'>Verify Mail</button></a>"+
						"<br><br> <p> please ignore this mail if your not registered. Thank you.</p> "
						+ "Dont Reply to this mail.</body></html>"
						, "UTF-8","html");
				
			}
		};
		
		javaMailSender.send(mimeMessagePreparator);
	}

    public String htmlHead()
    {
    	String head="<html><head>"+
    "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"+
  "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js'></script>"+
  "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>"
    			
    			+ "</head><body>";
    	
    	
    	return head;
    }
    
	public void sendMailForBlog(Blog blog)
	{
		Users user=blog.getUsers();
		
		
		MimeMessagePreparator mimeMessagePreparator=new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
				mimeMessage.setFrom(new InternetAddress("ebinezer.p87@gmail.com"));
				mimeMessage.setSubject("Blog Status");
				if(blog.getStatus().equalsIgnoreCase("approved"))
				{
				mimeMessage.setText(htmlHead()+"<p>We are happy to announce you that your blog"+blog.getBlogTitle()+"has been posted.</p>"
						+ "Thanks"
						, "UTF-8","html");
				}else
				{
					mimeMessage.setText(htmlHead()+"<p>We are regreat to announce you that your blog"+blog.getBlogTitle()+"has not been approved to post.</p>"
							+ "Thanks"
							, "UTF-8","html");
				}
			}
		};
		
		javaMailSender.send(mimeMessagePreparator);
	}
	
	
}
