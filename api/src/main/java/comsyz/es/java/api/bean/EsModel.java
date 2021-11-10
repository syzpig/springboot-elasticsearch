package comsyz.es.java.api.bean;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 *  @author: syz
 *  @Date: 2021/11/2 16:29
 *  @Description:
 */ 

@Data
@ToString
@NoArgsConstructor
public class EsModel {

    private String id;

    private String name;

    private int age;

    private Date date;
}
