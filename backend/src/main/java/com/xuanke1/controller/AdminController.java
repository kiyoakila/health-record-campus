package com.xuanke1.controller;

import com.alibaba.fastjson.JSON;
import com.xuanke1.bean.*;
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
public class AdminController {
    @Autowired
    ResidentDao ad;
    @Autowired
    UserDao ud;
    @Autowired
    ApplicationDao apd;
    @Autowired
    VisitorDao vd;
    List<Application> la;
    List<Resident> lr;
    List<Visitor> lv;



    //添加住户
    @RequestMapping("/addr")
    public String addr(@RequestBody Resident r){
        String flag="error";

        System.out.println("r:"+r);

        //获得所有
        lr=ad.getRidAll();
        int id=0;
        for(int i=0;i<lr.size();i++){
            Resident rt=lr.get(i);
            if(rt.getId()>id)
                id=rt.getId();

        }
        id++;
        System.out.println("id:"+id);

        //添加住户
        flag="true";
        ad.insertR(id,r.getName(),r.getIdcard(),r.getSex(),r.getAddress(),r.getPhone(),r.getTemperature());


        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);
        res.put("r",r);

        String res_json= JSON.toJSONString(res);
        System.out.println("flag:"+flag);
        return res_json;

    }



    //获取所有住户
    @RequestMapping("/lr")
    public String getRAll(){
        lr=ad.getRAll();
        System.out.println("lr");

        String flag="success";
        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);
        res.put("lr",lr);

        String res_json= JSON.toJSONString(res);
        return res_json;
    }

    @RequestMapping("/upr")
    public String updateR(@RequestBody Resident r){
        System.out.println("r:"+r);
        ad.updateR(r.getName(),r.getIdcard(),r.getSex(),r.getAddress(),r.getPhone());


        String flag="success";
        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);
        res.put("r",r);

        String res_json= JSON.toJSONString(res);
        return res_json;
    }

    @RequestMapping("/uprpwd")
    public boolean updateRpwd(@RequestBody User u){
        System.out.println("u:"+u);
        ud.updateUserpwd(u.getUsername(),u.getPassword());

        boolean flag=true;

        return flag;
    }



    //删除学生
    @RequestMapping("/deleter")
    public String deleteR(@RequestBody Resident r){
        ad.deleteR(r.getIdcard());
        String flag="true";


        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);
        res.put("r",r);

        String res_json= JSON.toJSONString(res);
        return res_json;
    }

    @RequestMapping("/audit")
    public String Audit(){
        la=apd.getApplicationAllAuditing(0);
        System.out.println("la:"+la);

        for(int i=0;i<la.size();i++){
            la.get(i).setUsername(ad.getNameById(la.get(i).getRid()));

        }

        String flag="success";
        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);
        res.put("la",la);

        String res_json= JSON.toJSONString(res);
        return res_json;
    }

    @RequestMapping("/upaa")
    public String updateAa(@RequestBody Application a ){
        System.out.print("a:"+a);
        apd.updateAA(a.getId(),1);

        String flag="success";
        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);

        String res_json= JSON.toJSONString(res);
        return res_json;
    }


    @RequestMapping("/upad")
    public String updateAd(@RequestBody Application a ){
        System.out.print("a:"+a);
        apd.updateAA(a.getId(),2);

        String flag="success";
        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);

        String res_json= JSON.toJSONString(res);
        return res_json;
    }

    @RequestMapping("/updateAdminpwd")
    public boolean updateAdminpwd(@RequestBody HashMap<String,String> mpwd){


        System.out.println("mpwds:"+mpwd);

        String oldpass=mpwd.get("oldpass");
        String pass=mpwd.get("checkPass");

        System.out.println("oldpass"+oldpass);
        System.out.println("pass:"+pass);


        User u=ud.getUserByName("admin");
        String cpass=u.getPassword();
        System.out.println("cpass:"+cpass);
        //判断输入密码是否正确
        if(!oldpass.equals(cpass)){
            return false;
        }
//        System.out.println("sjhm:"+sjhm);
        boolean flag=false;
        ud.updateUserpwd("admin",pass);
        flag=true;


        return flag;

    }

    @RequestMapping("/getCommunity")
    public String getCommunity(){
        lr=ad.getRAll();
        System.out.println("lr");


        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        String time=createdate.substring(0,10);
        System.out.println("time"+time);

        la=apd.getApplicationByTime(time);
        int n2=la.size();
        System.out.println("n2"+n2);
        la=apd.getApplicationByActualTime(time);
        int n1=la.size();
        System.out.println("n1"+n1);
        lv=vd.getAllVisitorByTime(time);
        int n3=lv.size();




        String flag="success";
        HashMap<String,Object> res=new HashMap();

        res.put("flag",flag);
        res.put("lr",lr);
        res.put("n1",n1);
        res.put("n2",n2);
        res.put("n3",n3);

        String res_json= JSON.toJSONString(res);
        return res_json;
    }



}
