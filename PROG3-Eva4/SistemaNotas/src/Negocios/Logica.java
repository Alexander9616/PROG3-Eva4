package Negocios;

import Controlador.Conexion;
import java.sql.ResultSet;

public class Logica {

    ResultSet resultado;
    
    String carnet,nombre,codMateria,nombreMateria,nota1,nota2,nota3;
    int ciclo;
    
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }
    
    
    public boolean insertarMateria(){        
        String consultaSQl = "";
        consultaSQl = "insert into tblMaterias(codMateria,nombre) ";
        consultaSQl += "values('"+getCodMateria()+"','"+getNombreMateria()+"') ";
        consultaSQl += "insert into tblNotas(codCiclo)";
        Conexion objControlador = new Conexion();
        return objControlador.ejecutarConsultaSql(consultaSQl);
    }
    
    
    public boolean validarMateria(){        
        String consultaSql = "select nombre from tblmaterias where codMateria = '" + getCodMateria() + "'";
        boolean estado = false;
        try {
            Conexion objConexion = new Conexion();
            ResultSet objResultado = objConexion.consultaCarnet(consultaSql);
            if(objResultado.next()){
                setNombre(objResultado.getString(1).toString());
                setCarnet(objResultado.getString(1).toString());
                estado = true;
                //objConexion.cerrarConexionMySql();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return estado;
    }
    
     public String consultarMateria(){        
        String consultaSql = "select nombre from tblMaterias where codMateria = '" + getCodMateria() + "'";
        
        try {
            Conexion objConexion = new Conexion();
            ResultSet objResultado = objConexion.consultaCarnet(consultaSql);
            if(objResultado.next()){
                setNombre(objResultado.getString(1).toString());
                return getNombre();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
     
     
     
     
     public boolean validarCarnet(){        
        String consultaSql = "select nombre from tblestudiantes where carnet = '" + getCarnet() + "'";
        boolean estado = false;
        try {
            Conexion objConexion = new Conexion();
            ResultSet objResultado = objConexion.consultaCarnet(consultaSql);
            if(objResultado.next()){
                setNombre(objResultado.getString(1).toString());
                setCarnet(objResultado.getString(1).toString());
                estado = true;
                //objConexion.cerrarConexionMySql();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return estado;
    }
    
     public String consultarCarnet(){        
        String consultaSql = "select nombre from tblestudiantes where carnet = '" + getCarnet() + "'";
        
        try {
            Conexion objConexion = new Conexion();
            ResultSet objResultado = objConexion.consultaCarnet(consultaSql);
            if(objResultado.next()){
                setNombre(objResultado.getString(1).toString());
                return getNombre();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
     
     
     
     
    public boolean eliminarUsuario(String usuario)
    {
        String consulta = "delete from usuarios where usuario = '"+usuario.trim()+"'";
        try{
            Conexion objConexion = new Conexion();
            return objConexion.ejecutarConsultaSql(consulta);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    public boolean actualizarUsuario(String usuario, String nombre, String clave, int nivel)
    {
        String consulta = "update usuarios set nombre = '"+nombre+"', clave = '"+clave+"', nivel = "+nivel+" ";
        consulta += "where usuario = '"+usuario+"'";
        Conexion conexion = new Conexion();
        try{
            return conexion.ejecutarConsultaSql(consulta);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ResultSet BuscarNotas()
    {
        Conexion conn=new Conexion();
        String consulta="SELECT n.codCiclo as Ciclo,m.codMateria as Cod_Materia,m.nombre as Materia ";
                consulta +=",n.nota1,n.nota2,n.nota3,sum(n.nota1+n.nota2+n.nota3)/3 as Promedio ";
                consulta +="from tblnotas as n inner join tblmaterias as m ";
                consulta +="on n.codMateria=m.codMateria WHERE n.carnet='"+getCarnet()+"' and n.ciclo= '"+getCiclo()+"' ";
        ResultSet datos=null;
        try
        {
            datos=conn.consultaCarnet(consulta);
            datos.next();
        }
        catch(Exception ex)
        {
            datos=null;
        }
        return datos;
    }
    
    
    
   /* public String verClave(String usuario){        
        String consultaSql = "select clave from usuarios where usuario = '" + usuario + "'";
        
        try {
            Conexion objConexion = new Conexion();
            ResultSet objResultado = objConexion.consultaCarnet(consultaSql);
            if(objResultado.next()){
                setUsuario(objResultado.getString(1).toString());
                return getUsuario();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public String verNivel(String usuario){        
        String consultaSql = "select nivel from usuarios where usuario = '" + usuario + "'";
        
        try {
            Conexion objConexion = new Conexion();
            ResultSet objResultado = objConexion.consultaUsuario(consultaSql);
            if(objResultado.next()){
                setUsuario(objResultado.getString(1).toString());
                return getUsuario();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }*/
    
}
