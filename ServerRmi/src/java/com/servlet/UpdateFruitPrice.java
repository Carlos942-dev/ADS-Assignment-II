package com.servlet;

import com.mysql.cj.xdevapi.JsonArray;
import com.rmi_bean.FruitModel;
import com.rmi_interface.Compute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "UpdateFruitPrice", urlPatterns = {"/UpdateFruitPrice"})
public class UpdateFruitPrice extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

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

                JSONObject obj = new JSONObject(recievedRequest.toString());
                FruitModel fruit = new FruitModel();
                fruit.setFruitName(obj.getString("fruitName"));
                fruit.setPrice(obj.getString("price"));
                fruit.setId(obj.getString("id"));
                service.update(fruit);
                Map<String, String> respMsg = new HashMap();
                respMsg.put("msg", "success");
                respMsg.put("code", "0");

                out.println(new JSONObject(respMsg));

                
            } catch (Exception e) {
                
                throw new Error(e.getMessage());
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
