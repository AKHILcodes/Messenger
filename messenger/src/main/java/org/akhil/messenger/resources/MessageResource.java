package org.akhil.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.akhil.messenger.model.Message;
import org.akhil.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMesssages(@QueryParam("year") int yearLocal,
									  @QueryParam("start") int start,
									  @QueryParam("size") int size){
		if(yearLocal > 0)
			return messageService.getAllMessagesForYear(yearLocal);
		if(start >= 0 && size > 0)
			return messageService.getAllMessagesPaginated(start, size);
		return messageService.getAllMessages();
	}
	
	@POST
	public Response addMessage(Message message){
		Message newMessage = messageService.addMessage(message);
		return Response.status(Status.CREATED).entity(newMessage).build();
		//return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{messageID}")
	public Message updateMessage(@PathParam("messageID") long id, Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageID}")
	public void deleteMessage(@PathParam("messageID") long id){
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id){
		return messageService.getMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
