package org.webservice;
import java.io.*;
import java.math.*;

import org.codehaus.jackson.map.deser.*;
import org.joda.time.*;
import org.lesstif.jira.issue.*;
import org.openxava.jpa.XPersistence;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.onerelease.model.*;
import org.slf4j.*;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;;

@Path("/")
public class EndPoint {
		@Path("/webhook")
	    @POST
	    @Consumes({MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    public Response IssueHandle(@QueryParam("user_id")String userId,
				@QueryParam("user_key")String UserKey,Webhook webhook) {
			Logger logger = LoggerFactory.getLogger(getClass());	
			logger.info("helllooooo");
			Ticket ticket;
			
			try {
			Query query = XPersistence.getManager().createQuery(
					"from Ticket as t where t.reference = :reference ");
					 query.setParameter("reference", webhook.getIssue().getKey());
					 ticket = (Ticket) query.getSingleResult();
			}
			catch(NoResultException e)
			{ticket = new Ticket();}
			BigInteger newRank = (BigInteger) XPersistence.getManager().createNativeQuery(
					"select count(*) from Ticket as t "
					+ "where t.name is not null ").getSingleResult();
			if(ticket.getRank() == BigInteger.ZERO) ticket.setRank(newRank);
	        ticket.setJiraId(webhook.getIssue().getId());
	        ticket.setReference(webhook.getIssue().getKey());
	        ticket.setCreationDate(webhook.getIssue().getFields().getCreated().toDate());
	        ticket.setSubjectAcknowledged(false);
	        ticket.setAckMeeting(false);
	        ticket.setSummary(webhook.getIssue().getFields().getSummary());
	        ticket.setProject(getProject(webhook.getIssue().getFields()
	        		.getProject().getKey()));
	        
	        if(webhook.getIssue().getFields().
	        		getIssuetype().getName().equals(IssueType.ISSUE_TYPE_EPIC))
	        		{
	        	      ticket.setName(webhook.getIssue().
	        	    		  getFields().getCustomfield().get("customfield_10011").toString());
	        		}
	        else if(! webhook.getIssue().
    	    		  getFields().getCustomfield().
    	    		  get("customfield_10014").toString().equals("null")) {
	        	ticket.setSubject(getSubject(webhook.getIssue().
      	    		  getFields().getCustomfield().
      	    		  get("customfield_10014").toString()));
	        }
	        
//	        XPersistence.getManager().persist(ticket);
	        ticket = XPersistence.getManager().merge(ticket);
//	        XPersistence.getManager().find(Ticket.class,ticket.getId());
//	        ticket.setAckMeeting(true);
	        return Response.status(200).entity("").build();
		}
		
		private Ticket getSubject(String key) {
			Query query = XPersistence.getManager().createQuery(
	 				"from Subject as s where s.reference = :reference ");
	 				 query.setParameter("reference", key);
	 				Ticket ticket = (Ticket) query.getSingleResult();
			return ticket;
		}




		private Project getProject(String key) {
			Query query = XPersistence.getManager().createQuery(
	 				"from Project as p where p.reference = :reference ");
	 				 query.setParameter("reference", key);
	 				Project project = (Project) query.getSingleResult();
			return project;
		}
		
		
		
		
		
		
		
		@Path("/childTicket")
	    @POST
	    @Consumes({MediaType.APPLICATION_JSON,  MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    public Response IssueChildHandle(@QueryParam("user_id")String userId,
				@QueryParam("user_key")String UserKey,Webhook webhook) {
	        Ticket ticket = new Ticket();
	        ticket.setJiraId(webhook.getIssue().getId());
	        ticket.setReference(webhook.getIssue().getKey());
	        Query query = XPersistence.getManager().createQuery(
				"from Ticket as t where t.jiraId = :jiraId ");
				 query.setParameter("jiraId", webhook.getChangelog().
						 getItems().get(0).getTo());
				 Ticket subject = (Ticket) query.getSingleResult();
	        ticket.setSubject(subject);
	        ticket.setCreationDate(webhook.getIssue().getFields().getCreated().toDate());
	        ticket.setSubjectAcknowledged(false);
	        ticket.setAckMeeting(false);
	        //XPersistence.getManager().merge(ticket);
	        XPersistence.getManager().persist(ticket);
	        return Response.status(200).entity("").build();
		}
		
		
		@Path("/jira")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON) 
		public Response postMsg(@QueryParam("user_id")String userId,
				@QueryParam("user_key")String UserKey,String h) {
			Logger logger = LoggerFactory.getLogger(getClass());	
		    logger.info("aloooooooo");
		    System.out.println("################################");
		    System.out.println(h);
		    String output = h;
		    return Response.status(200).entity(output).build();
		}
		
		@GET
		@Path("/verify")
		@Produces(MediaType.TEXT_PLAIN)
		public Response verifyRESTService(InputStream incomingData) {
			String result = "RESTService Successfully started..";
	 
			// return HTTP response 200 in case of success
			return Response.status(200).entity(result).build();
		}
}
