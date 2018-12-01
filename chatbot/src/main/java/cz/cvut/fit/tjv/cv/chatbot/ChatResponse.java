package cz.cvut.fit.tjv.cv.chatbot;

import java.util.Date;
import java.util.Objects;

public class ChatResponse {
    private final String response;
    private int usage; // statistika
    private final Date timestamp = new Date(); // cas zalozeni zpravy

    public ChatResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    /**
     * pri kazdem pouziti response vypocitam statistiku
     * @return response
     */
    public String useResponse() {
        usage++;
        return response;
    }
    
    public int getUsage() {
        return usage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.response);
        hash = 17 * hash + this.usage;
        hash = 17 * hash + Objects.hashCode(this.timestamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChatResponse other = (ChatResponse) obj;
        if (this.usage != other.usage) {
            return false;
        }
        if (!Objects.equals(this.response, other.response)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
