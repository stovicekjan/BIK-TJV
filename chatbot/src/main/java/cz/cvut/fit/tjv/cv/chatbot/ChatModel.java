package cz.cvut.fit.tjv.cv.chatbot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

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
    
    public double averageUse() {
        OptionalDouble oRes = database.values()
                .stream()
//                .mapToInt((ChatResponse arg0) -> {return arg0.getUsage();})
                .mapToInt(c -> c.getUsage()) 
                .average();
        if (oRes.isPresent())
            return oRes.getAsDouble();
        return oRes.orElse(0);
    }
                
    /**
     * vrati responses o urcite minimalni delce, serazene podle cetnosti pouziti
     * @param minLength
     * @return 
     */
    public List<String> responseOrdered(int minLength) {
        return database.values()
                .stream()
//                .sorted(Comparator.comparing(c -> c.getUsage()));
                .sorted(Comparator.comparing(ChatResponse::getUsage))
                .map(c -> c.getResponse())
                .filter(r -> r.length() >= minLength)
                .collect(Collectors.toList());
    }
    
}
