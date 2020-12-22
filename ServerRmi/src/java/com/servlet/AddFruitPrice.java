package com.servlet;

import com.mysql.cj.xdevapi.JsonArray;
import com.rmi_bean.FruitModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.rmi_interface.Compute;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class AddFruitPrice extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("we are her");

            try {

                StringBuilder recievedRequest = new StringBuilder();
                String line;

                JsonArray myArray;

                try (BufferedReader reader = request.getReader()) {
                    while ((line = reader.readLine()) != null) {
                        recievedRequest.append(line);
                    }
                }

                Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                Compute service = (Compute) reg.lookup("SERVER");
                System.out.println("request" + recievedRequest);
                JSONObject obj = new JSONObject(recievedRequest.toString());
                FruitModel fruit = new FruitModel();
                fruit.setFruitName(obj.getString("fruitName"));
                fruit.setPrice(obj.getString("price"));
                service.add(fruit);
                Map<String, String> respMsg = new HashMap();
                respMsg.put("msg", "success");
                respMsg.put("code", "0");
                out.println(new JSONObject(respMsg));
            } catch (Exception e) {
                        System.out.println("we are her");
                throw new Error(e.getMessage());
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         System.out.println("serasdcver ..........");
        doGet(request, response);
    }

  

}
