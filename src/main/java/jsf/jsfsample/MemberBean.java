/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf.jsfsample;

import java.io.Serializable;
import java.util.Optional;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author naoki
 */
@Named
@SessionScoped
public class MemberBean implements Serializable{
    @Setter @Getter
    Member edit = null;
    
    Optional<Member> original = Optional.empty();
    
    public String startEdit(Member org){
        original = Optional.of(org);
        
        edit = new Member(org.memberName, org.type);
        
        return "detail";
    }
    
    public String startCreate(){
        original = Optional.empty();
        return "detail";
    }
    
    public boolean isEdit(){
        return original.isPresent();
    }
}
