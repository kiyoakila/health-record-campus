package com.xuanke1.controller;


import com.alibaba.fastjson.JSON;
import com.xuanke1.bean.Application;
import com.xuanke1.bean.Resident;
import com.xuanke1.bean.Visitor;
import com.xuanke1.dao.ApplicationDao;
import com.xuanke1.dao.ResidentDao;
import com.xuanke1.dao.UserDao;
import com.xuanke1.dao.VisitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class VisitorController {
    @Autowired
    VisitorDao vd;
    List<Visitor> lv;

    @RequestMapping("/addv")
    public String addv(@RequestBody Visitor v){
        String flag="error";

        System.out.println("v:"+v);

        //获得所有
        lv=vd.getAllVisitor();
        int id=0;
        for(int i=0;i<lv.size();i++){
            Visitor vt=lv.get(i);
            if(vt.getId()>id)
                id=vt.getId();

        }
        id++;
        System.out.println("id:"+id);


        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);

        //添加住户
        flag="true";
        vd.insertVisitor(id,v.getName(),v.getSex(),v.getIdcard(),v.getPhone(),"0",createdate,v.getReason(),v.getTemperature());


        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);
        res.put("v",v);

        String res_json= JSON.toJSONString(res);
        System.out.println("flag:"+flag);
        return res_json;

    }


}
