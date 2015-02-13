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
    
    @Getter
    SocialNet editingSns = null;
    
    Optional<Member> original = Optional.empty();
    
    @Inject
    Repository repository;
    
    public String startEdit(Member org){
        original = Optional.of(org);
        
        editing = new Member(org.memberName, org.type);
        for(SocialNet sn : org.socials){
            editing.socials.add(new SocialNet(sn.name, sn.account));
        }
        
        return "detail";
    }
    
    public String startCreate(){
        original = Optional.empty();
        editing = new Member();
        return "detail";
    }
    
    public String doCreate(){
        return "list?faces-redirect=true";
    }
    
    public String doEdit(){
        return "list?faces-redirect=true";
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
    
    public void searchZipcode(){
        String[] pref = {"北海道", "東京都", "神奈川県" , "長野県", "愛知県", "大阪府", "京都府", "広島県", "福岡県", "沖縄県"};
        String[] city = {"札幌市", "千代田区", "横浜市", "長野市", "名古屋市", "箕面市", "京都市中京区", "広島市安佐南区", "福岡市博多区", "那覇市"};

        Optional.ofNullable(editing)
                .map(m -> m.zipCode)
                .filter(s -> !s.isEmpty())
                .map(s -> s.charAt(0) - '0')
                .filter(idx -> idx >= 0 && idx <= 9)
                .ifPresent(idx -> {
                    editing.address1 = pref[idx];
                    editing.address2 = city[idx];
                });
    }
    
    public void startSnsCreate(){
        editingSns = new SocialNet();
    }
    
    public void doSnsCreate(){
        editing.getSocials().add(editingSns);
    }
    
    public boolean isEdit(){
        return original.isPresent();
    }
    
    public boolean isPersonal(){
        return editing.getType() == MemberType.PERSONAL;
    }
    
    public MemberType[] getMemberTypes(){
        return MemberType.values();
    }
    
    public EnumConverter<MemberType> getMemberTypeConverter(){
        return new EnumConverter<>(MemberType.class);
    }
}
