/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remota;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author SAINZ
 */
public class Remota {

//package conexionremota;

//public class ConexionRemota {
     static Scanner sc=new Scanner(System.in);
     static int cantidad;
    public static void main(String[] args) {
        //3insercion();
        showMenu();
    
    }

    private static void showMenu() {
        Scanner sc = new Scanner(System.in);
        int opc;
        int opcion;
        do{
            System.out.println("Seleccione la de base de datos a la que quiera acceder");
            System.out.println("1. MySQL");
            System.out.println("2. SQL Server");
            System.out.println("3. DB2");
            System.out.println("4. PostgreSQL");
            System.out.print("Opcion:");
            opc = sc.nextInt();
            switch(opc){
                case 1:
                    mysql();
                break;
                case 2:
                    sqlServer();
                break;
                case 3:
                    db2();
                break;
                case 4:
                    postgresql();
                break;
            } 
            System.out.println("Desea acceder a otra base de datos? (si=1/no=2)");
            opcion = sc.nextInt();
        }while(opcion == 1);
    }

    private static void mysql() {
        System.out.println("Ingrese la cantidad a insertar:");
        cantidad=sc.nextInt();
        for(int i=1;i<=cantidad;i++){
            try{
            //String sSQL = "INSERT INTO ciudad (nombre) VALUES ('ciudad"+i+"');";
            String url = "jdbc:mysql://localhost:3308/lmcd";
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, "test", "123456");

            Statement statement = connection.createStatement();
            //statement.execute("INSERT INTO ciudad (nombre) values ('ciudad"+i+"');");
            int ram=(int)(Math.random()*100)+1;
            statement.execute("INSERT INTO alumno (nombre,idCiudad) VALUES ('alumno"+i+"','"+ram+"')");
            //System.out.println("Prueba mariadb");
            /*while(rs.next()){
                System.out.println(rs.getInt(1)+", "+rs.getString(2)+", ('");
            }*/
            connection.close();
            
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    private static void sqlServer(){
        //String alumno="Alumno";
        System.out.println("Ingrese la cantidad a insertar:");
        cantidad=sc.nextInt();
        //int cantidadCiudad;
        long TInicio , TFin, tiempo;
        TInicio = System.currentTimeMillis();
        for(int i=1;i<=cantidad;i++){
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.13:1433;databaseName=LMCD","sa","171114");
            Statement st = conn.createStatement();
            /*ResultSet con=st.executeQuery("select count(*) from Ciudad");
              System.out.println(con);*/
             //System.out.println(""+i);
            //INSERTANDO DATOS TABLA ALUMNO  
                int ram=(int)(Math.random()*100)+1;
                st.executeQuery("INSERT INTO Alumno (Nombre,idCiudad) values('Alumno"+i+"','"+ram+"')");
            //INSERTANDO DATOS TABLA CIUDAD      
                //st.executeQuery("INSERT INTO Ciudad (Ciudad) values('Ciudad"+i+"')");
                //System.out.println("entra");
                           // while(rs.next()){
                //System.out.println(rs.getInt(1)+", "+rs.getString(2));
            //}
            } catch(Exception ex){
                //System.out.println(ex);
            }
        }TFin = System.currentTimeMillis();
        tiempo = TFin - TInicio;
        System.out.println("Tiempo de insertar en milisegundos: " + tiempo);
        
    }

    private static void db2() {
       // System.out.println("Ingrese la cantidad a insertar:");
       // cantidad=sc.nextInt();
       // long TInicio , TFin, tiempo;
       // TInicio = System.currentTimeMillis();
       // for(int i=1;i<=cantidad;i++){
                try{
                Class.forName("com.ibm.db2.jcc.DB2Driver");
                Connection conn = DriverManager.getConnection("jdbc:db2://192.168.43.252:50000/INVERD", "db2admin", "171120");
                Statement st = conn.createStatement();
            //INSERTANDO DATOS TABLA ALUMNO  
                //int ram=(int)(Math.random()*100)+1;
                //String sSQL = "INSERT INTO Alumno (Nombre,idCiudad) values('Alumno"+i+"','"+ram+"')";
            //INSERTANDO DATOS TABLA CIUDAD      
                //String sSQL = "INSERT INTO Ciudad (Ciudad) values('Ciudad"+i+"')";
            //---------------------------------------------------------------------------------------------- 
            //CREANDO LA BASE DE DATOS   
                //st.execute("Create DataBase INVERD"); 
            //TABLA TIPOPRODUCTO
                st.execute("create table tipoproducto(idtipo int not null GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1),tipo varchar(50),PRIMARY KEY(idtipo))");                  
            //TABLA PRODUCTO   
               // st.execute("create table producto(idproducto int not null GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1),nombreproducto varchar(50),tipoproducto varchar(50), condicionactual varchar(50),fechaingreso date,PRIMARY KEY(idproducto))");                  
            //TABLA REGISTRORIEGO    
               // st.execute("create table registroriego(idriego int not null GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1),fechariego date,PRIMARY KEY(idriego))");                  
            //TABLA HISTORIAL
               // st.execute("create table historial(idhistorial int not null GENERATED ALWAYS AS IDENTITY(START WITH 1 INCREMENT BY 1),fechahistorial date, fotografia varchar(50),PRIMARY KEY(idhistorial))");                  
                //System.out.println("Listo");
                //st.execute(sSQL);
                conn.close();
            } catch(Exception ex){
                System.out.println(ex);
            }
        //}
        //TFin = System.currentTimeMillis();
        //tiempo = TFin - TInicio;
        //System.out.println("Tiempo de insertar en milisegundos: " + tiempo);
    }

    private static void postgresql() {
       // System.out.println("Ingrese la cantidad a insertar:");
       // cantidad=sc.nextInt();
       // long TInicio , TFin, tiempo;
       // TInicio = System.currentTimeMillis();
       // for(int i=1;i<=cantidad;i++){
            //String ciudad="ciudad";
            Connection conn = null;
            String urlDatabase = "jdbc:postgresql://192.168.1.105:5432/viveromoises";
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(urlDatabase, "sainz", "1234");
                Statement st = conn.createStatement();
            //INSERTANDO DATOS TABLA CIUDAD    
               // st.executeQuery("INSERT INTO ciudad(nombre) values ('ciudad"+i+"')");
            //INSERTANDO DATOS TABLA ALUMNO       
               //int ram=(int)(Math.random()*100)+1;
               //st.executeQuery("INSERT INTO alumno(nombre,idCiudad) values ('Alumno"+i+"','"+ram+"')");
            //----------------------------------------------------------------------------------------------   
            //CREANDO LA BASE DE DATOS   
               // st.execute("Create DataBase viveromoises"); 
            //CREANDO LAS PRIMARY KEY
                //st.execute("create table historial(idhistorial int default nextval('historial_sec') primary key, fechahistorial date, fotografia varchar(50));");
            //TABLA TIPOPRODUCTO
                /*st.execute("create sequence tipoproducto_sec;"
                         + "create table tipoproducto(idtipo int default nextval('tipoproducto_sec') primary key, tipo varchar(50));");
                */    
            //TABLA PRODUCTO   
                /*st.execute("create sequence producto_sec;"
                         + "create table producto(idProducto int default nextval('producto_sec') primary key, nombreProducto varchar(50), tipoProducto varchar(50), condicionActual varchar(50), fechaIngreso date);");
                */
            //TABLA REGISTRORIEGO    
               /* st.execute("create sequence registroriego_sec;" 
                         + "create table registroriego(idriego int default nextval('registroriego_sec'), fechariego date default null,producto varchar(50));");*/
            //TABLA HISTORIAL
                /*st.execute("create sequence historial_sec;"   
                         + "create table historial(idhistorial int default nextval('historial_sec'), fechahistorial date default null,fotografia varchar(50));");*/
                //System.out.println("Listo");
                /*while (rs.next()) {
                    System.out.println(rs.getInt(1)+", "+rs.getString(2));
                }*/
                conn.close();

            } catch (Exception e) {
                System.out.println(e);
            }
        //}
        //TFin = System.currentTimeMillis();
        //tiempo = TFin - TInicio;
        //System.out.println("Tiempo de insertar en milisegundos: " + tiempo);
    }  
}


