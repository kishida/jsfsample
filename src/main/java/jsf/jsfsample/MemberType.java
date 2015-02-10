/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.jsfsample;

/**
 *
 * @author kishida
 */
public enum MemberType {
    PERSONAL("個人"), COMPANY("法人");
    String displayName;
    private MemberType(String disp) {
        displayName = disp;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    
}
