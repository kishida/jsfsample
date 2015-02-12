/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf.jsfsample;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Optional;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author naoki
 */
@Named
@SessionScoped
public class MemberBean implements Serializable{
    @Setter @Getter
    Member editing = null;
    
    @Setter @Getter
    Member selected;
    
    Optional<Member> original = Optional.empty();
    
    @Inject
    Repository repository;
    
    public String startEdit(Member org){
        original = Optional.of(org);
        
        editing = new Member(org.memberName, org.type);
        
        return "detail";
    }
    
    public String startCreate(){
        original = Optional.empty();
        return "detail";
    }
    
    public void doDelete(){
        boolean success = false;
        for(Iterator<Member> ite = repository.getMembers().iterator(); ite.hasNext();){
            Member m = ite.next();
            if(m.type == MemberType.PERSONAL && m.getMemberName().equals(selected.getMemberName())){
                ite.remove();
                success = true;
                break;
            }
        }
        RequestContext.getCurrentInstance().addCallbackParam("isSuccess", success);
    }
    
    
    public boolean isEdit(){
        return original.isPresent();
    }
    
    public MemberType[] getMemberTypes(){
        return MemberType.values();
    }
    
    public EnumConverter<MemberType> getMemberTypeConverter(){
        return new EnumConverter<>(MemberType.class);
    }
}
