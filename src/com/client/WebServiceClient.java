package com.client;

import com.dto.SysUser;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.utils.StringUtils;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class WebServiceClient {

    /**
     * @param args
     * @throws AxisFault
     */

    private static String endpoint = "http://localhost:8080/services/TableInfoService";

    @Test
    public void testGetName() {
        String method = "getName";
        String[] parms = new String[]{"gaffey"};
        WebServiceClient webClient = new WebServiceClient();

        String svrResult = webClient.CallMethodStr(endpoint, method, parms);

        System.out.println("return value is " + svrResult);
    }

    @Test
    public void testGetOne() {
        String method = "getOne";
        String[] parms = new String[]{};
        WebServiceClient webClient = new WebServiceClient();

        SysUser sysUser = webClient.CallMethodUser(endpoint, method, parms);

        System.out.println(sysUser.getUserId() + "\t" + sysUser.getUserName());
    }

    @Test
    public void testGetByName() {
        String method = "getByName";
        String[] parms = new String[]{"ADMIN"};
        WebServiceClient webClient = new WebServiceClient();

        String svrResult = webClient.CallMethodStr(endpoint, method, parms);

        System.out.println("return value is " + svrResult);
    }

    @Test
    public void testGetAll() {
        String method = "getUserAll";
        String[] parms = new String[]{};
        WebServiceClient webClient = new WebServiceClient();

        List list = webClient.CallMethodList(endpoint, method, parms);

        System.out.println("list.size=" + list.size());
        for (int i = 0; i < list.size(); i++) {
            SysUser s = (SysUser) list.get(i);
            System.out.println(s.getUserId() + "\t" + s.getUserType() + "\t" +
                    s.getUserName() + "\t" + s.getPassword() + "\t" + s.getEmail() + "\t" + s.getPhone()
                    + "\t" + s.getStartActiveDate() + "\t" + s.getEndActiveDate()
                    + "\t" + s.getStatus());
        }
    }

    public String CallMethodStr(String url, String method, Object[] args) {
        String result = null;

        if (StringUtils.isEmpty(url)) {
            return "url地址为空";
        }

        if (StringUtils.isEmpty(method)) {
            return "method地址为空";
        }

        Call rpcCall = null;

        try {
            //实例websevice调用实例
            Service webService = new Service();
            rpcCall = (Call) webService.createCall();
            rpcCall.setTargetEndpointAddress(new java.net.URL(url));
            rpcCall.setOperationName(method);

            //执行webservice方法
            result = (String) rpcCall.invoke(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public SysUser CallMethodUser(String url, String method, Object[] args) {
        SysUser sysUser = null;

        Call rpcCall = null;

        try {
            //实例websevice调用实例
            Service webService = new Service();
            rpcCall = (Call) webService.createCall();

            //注册SimpleObject的序列化类型
            QName qn = new QName("urn:TableInfoService", "SysUser");
            rpcCall.registerTypeMapping(SysUser.class, qn,
                    new BeanSerializerFactory(SysUser.class, qn),
                    new BeanDeserializerFactory(SysUser.class, qn));

            QName getObjectQn = new QName(url, method);
            rpcCall.setOperationName(getObjectQn);
            rpcCall.setTargetEndpointAddress(new java.net.URL(url));
            rpcCall.setOperationName(method);

            //执行webservice方法
            sysUser = (SysUser) rpcCall.invoke(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUser;
    }

    public List CallMethodList(String url, String method, Object[] args) {
        List list = new ArrayList();

        Call rpcCall = null;

        try {
            //实例websevice调用实例
            Service webService = new Service();
            rpcCall = (Call) webService.createCall();

            //注册SimpleObject的序列化类型
            QName qn = new QName("urn:TableInfoService", "SysUser");
            rpcCall.registerTypeMapping(SysUser.class, qn,
                    new BeanSerializerFactory(SysUser.class, qn),
                    new BeanDeserializerFactory(SysUser.class, qn));

            QName getObjectQn = new QName(url, method);
            rpcCall.setOperationName(getObjectQn);
            rpcCall.setTargetEndpointAddress(new java.net.URL(url));
            rpcCall.setOperationName(method);

            //可根据要求设置用户名密码
            //rpcCall.setUsername();
            //rpcCall.setPassword();

            //执行webservice方法
            rpcCall.setReturnClass(ArrayList.class);
            list = (ArrayList) rpcCall.invoke(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}