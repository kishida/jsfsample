/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.jsfsample;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author kishida
 */
@Data
@NoArgsConstructor
public class Member {
    String memberName;
    MemberType type;
    String parentName;
    String representative;
    String telephone;
    String fax;
    boolean allowMail;
    String mailAddress;
    String zipCode;
    String address1;
    String address2;
    List<SocialNet> socials;
    
    public Member(String name, MemberType type){
        this.memberName = name;
        this.type = type;
    }
}
