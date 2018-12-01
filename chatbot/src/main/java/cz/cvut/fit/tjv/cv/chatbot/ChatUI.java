package cz.cvut.fit.tjv.cv.chatbot;

import java.util.Scanner;

public class ChatUI {
    private ChatModel model = new ChatModel();
    
    public void ui() { // infinite loop
        Scanner s = new Scanner(System.in);
        String line; // buffer pro zapis
        while ((line = s.nextLine()) != null) {
            try {
                System.out.println(model.chat(line));
            } catch (UnknownResponseException e) {
                System.out.println("Unknown message. Enter desired reply:");
                String reply = s.nextLine();
                model.submitChatResponse(line, reply);
                
            }
            
        }
    }
    
    public static void main(String[] args) {
        new ChatUI().ui();
    }
}
