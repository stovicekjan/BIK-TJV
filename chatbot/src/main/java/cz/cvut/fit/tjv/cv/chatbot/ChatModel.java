package cz.cvut.fit.tjv.cv.chatbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChatModel {
    private final Map<String, ChatResponse> database = new HashMap(); // final: muzu priradit jenom jednou, ale muzu do toho pridavat
    private final List<String> history = new ArrayList<>();
    
    public void submitChatResponse(String msg, String resp) {
        if (msg == null || resp == null)
            throw new NullPointerException("msg nor resp cannot be null");
        
        if (msg.isEmpty() || resp.isEmpty())
            throw new IllegalArgumentException("msg nor resp cannot be empty");
        
        database.put(msg, new ChatResponse(resp));
        
    }
    
    public String chat(String msg) {
        Objects.requireNonNull(msg); // if msg == null, throw NullPointerException
        
        if (database.containsKey(msg)) {
            history.add(msg);
            return database.get(msg).useResponse();
        }
        
        else
            throw new UnknownResponseException("message unknown");
    }
    
    public boolean isAlreadyUsed(String msg) {
        return (database.containsKey(msg) && database.get(msg).getUsage() > 1);
    }
}
