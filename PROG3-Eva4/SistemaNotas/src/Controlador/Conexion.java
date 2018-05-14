
package Controlador;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Conexion {
    Connection objConexionMySql;
    Statement ejecutaSentencia;

    Connection abrirConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            objConexionMySql = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/notas", "root", "");            
            ejecutaSentencia = (Statement)objConexionMySql.createStatement();
        } catch (Exception e) {
            objConexionMySql = null;
            System.out.println(e.getMessage() + " --- 3 ");
        }       
        return objConexionMySql;
    }
    public void cerrarConexion(){        
        try {
            if (objConexionMySql != null) {
                objConexionMySql.close();
            }
        } catch (Exception e) {
            objConexionMySql = null; 
            System.out.println(e.getMessage() + " --- 2 ");
        }
    }
    public boolean ejecutarConsultaSql(String consultaSql){
        boolean estado = false;
        try {
            abrirConexion();
            ejecutaSentencia.execute(consultaSql);
            estado = true;
        } catch (Exception e) {
            estado = false;
            System.out.println(e.getMessage() + " --- 1 ");
        }
        finally{
            cerrarConexion();
        }                
        return estado;
    }
    public ResultSet consultaCarnet(String consultaSql){
        ResultSet objResultado = null;
        try {
            abrirConexion();
            objResultado = ejecutaSentencia.executeQuery(consultaSql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        return objResultado;
    }
}
