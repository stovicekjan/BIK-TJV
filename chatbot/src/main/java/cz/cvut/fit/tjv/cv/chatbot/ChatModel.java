package cz.cvut.fit.tjv.cv.chatbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatModel {
    private final Map<String, ChatResponse> database = new HashMap(); // final: muzu priradit jenom jednou, ale muzu do toho pridavat
    private final List<String> history = new ArrayList<>();
    
    public void submitChatResponse(String msg, ChatResponse resp) {
        database.put(msg, resp);
    }
}
