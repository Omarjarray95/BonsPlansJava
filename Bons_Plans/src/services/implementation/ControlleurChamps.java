/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.implementation;

/**
 *
 * @author HP
 */
public class ControlleurChamps {
    
     public boolean isNumber(String s) {
        return s.matches("[0-9]*");

    }

    public boolean isName(String s) {
        return s.matches("([a-z]|[A-Z])*");
    }

    public boolean isEmail(String s) {
        return s.matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+");

    }

    public boolean isNum(String s) {
        return s.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
    }

    public boolean isFloat(String s) {
        return s.matches("\\d*\\.?\\d*");
    }
    public boolean isEmpty(String s){
         return (s.length()==0);
     }
}
