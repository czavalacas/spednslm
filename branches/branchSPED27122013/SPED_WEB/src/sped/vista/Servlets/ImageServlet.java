package sped.vista.Servlets;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import javax.sql.DataSource;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

public class ImageServlet extends HttpServlet {
    @SuppressWarnings("compatibility:-5668859933847456737")
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "image/jpg; charset=utf-8";
    @Resource(mappedName = "java:/app/jdbc/jdbc/SPED_RemotoDS")
    private DataSource spedDS;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        String nomusuario = request.getParameter("nomusuario");
        Connection conn = null;        
        Img(conn, response, nomusuario);
    }
    
    public void Img(Connection conn, HttpServletResponse response, String nomusuario){
        try {
            OutputStream os = response.getOutputStream();            
            conn = spedDS.getConnection();       
            String sql = "SELECT a.foto " +
                         "FROM admusua a " +
                         "WHERE a.usuario = '" + nomusuario + "' ";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                    int b;
                    byte[] buffer = new byte[10240];
                    while ((b = in.read(buffer, 0, 10240)) != -1) {
                        os.write(buffer, 0, b);
                    }
                    os.close();
                }else{
                    Img2(conn, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception sqle) {
                sqle.printStackTrace();
            }
        }
    }
    
    public void Img2(Connection conn, HttpServletResponse response){
        try {
            OutputStream os = response.getOutputStream();
            String sql = "SELECT a.foto FROM imagen a WHERE a.descripcion = 'imgdefecto'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
                    int b;
                    byte[] buffer = new byte[10240];
                    while ((b = in.read(buffer, 0, 10240)) != -1) {
                        os.write(buffer, 0, b);
                    }
                    os.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
