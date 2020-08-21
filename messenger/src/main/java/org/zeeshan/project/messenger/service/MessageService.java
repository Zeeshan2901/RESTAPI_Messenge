package org.zeeshan.project.messenger.service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.zeeshan.project.messenger.database.DatabaseClass;
import org.zeeshan.project.messenger.exception.DataNotFoundException;
import org.zeeshan.project.messenger.model.Message;


public class MessageService {
	
	
	public MessageService() {
		//messages.put(1L, new Message(1,"Hello", "Zeeshan"));
		//messages.put(2L, new Message(2,"World", "Zeeshan"));
		//messages.put(3L, new Message(3,"Jersey", "Zeeshan"));
		//messages.put(4L, new Message(4,"Java", "Zeeshan"));



	}

	private Map<Long, Message> messages =  DatabaseClass.getMessages();
	
	public List< Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Message with id : "+id +" not found.");
		}
		return message;
	}
	
	
	public List<Message> getAllMessagesForYear (int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal= Calendar.getInstance();
		for (Message message: messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR)== year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated (int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start +size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	
	
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public void removeMessage(long id) {
		messages.remove(id);
	}
	
	
}
