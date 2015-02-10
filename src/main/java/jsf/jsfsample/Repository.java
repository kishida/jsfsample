/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.jsfsample;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author kishida
 */
@Named
@ApplicationScoped
public class Repository {
    @PostConstruct
    public void init(){
        members = new ArrayList<>();
        members.add(new Member("きしだ", MemberType.PERSONAL));
        members.add(new Member("ほげ商会", MemberType.COMPANY));
    }
    
    @Getter
    List<Member> members;
}
