/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Ons Ben Othmen
 */
public class InterestedEvent {
   
    private int id;
    private int user;
    private int event;

    public InterestedEvent(int id, int user, int event) {
        this.id = id;
        this.user = user;
        this.event = event;
    }

    public InterestedEvent(int user, int event) {
        this.user = user;
        this.event = event;
    }

    public InterestedEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

  
    @Override
    public String toString() {
        return "InterestedEvent{" + "id=" + id + ", user=" + user + ", event=" + event + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.user);
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
        final InterestedEvent other = (InterestedEvent) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

   
    
}
