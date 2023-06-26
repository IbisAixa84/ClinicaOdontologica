package com.example.clase23.dao;

import com.example.clase23.model.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OdontologoDAOH2 implements IDao<Odontologo> {

    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS(NOMBRE, APELLIDO, MATRICULA) VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_BUSCAR="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_ELIMINAR="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_ACTUALIZAR="UPDATE ODONTOLOGOS SET ID=?, NOMBRE=?, APELLIDO=?, DOCUMENTO=?, WHERE ID=?";
    private static final String SQL_BUSCAR_NOMBRE="SELECT * FROM ODONTOLOGOS WHERE NOMBRE=?";
    private static final Logger LOGGER= Logger.getLogger(OdontologoDAOH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Guardo un odontologo");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps_insert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps_insert.setString(1, odontologo.getNombre());
            ps_insert.setString(2, odontologo.getApellido());
            ps_insert.setString(3, odontologo.getMatricula());
            ps_insert.execute();
            //ahora necesitamos recuperar el id
            ResultSet clave= ps_insert.getGeneratedKeys();
            while(clave.next()){
                odontologo.setId(clave.getInt(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Odontologo buscar(Integer id) {
        LOGGER.debug("Ingreso el metodo buscar odontologo por id");
        Connection connection= null;
        Odontologo odontologo= null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_BUSCAR);
            ps.setInt(1,id);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                //ID, matricula, nombre, apellido
                odontologo= new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        LOGGER.info("INGRESAMOS AL METODO ACTUALIZAR ID" + odontologo.getId());
        Connection conection= null;
        try{
            conection= BD.getConnection();
            PreparedStatement ps= conection.prepareStatement(SQL_ACTUALIZAR);
            //apellido,nombre,email
            ps.setInt(1, odontologo.getId());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.setString(4, odontologo.getMatricula());
            ps.execute();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                conection.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        LOGGER.debug("INGRESAMOS AL METODO DE ELIMINAR DE OSONTOLOGO" + id);
        Connection conection= null;
        try{
            conection= BD.getConnection();
            PreparedStatement ps= conection.prepareStatement(SQL_ELIMINAR);
            ps.setInt(1,id);
            ps.execute();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                conection.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection=null;
        List<Odontologo> listaOdontologos= new ArrayList<>();
        Odontologo odontologo;
        try{
            connection=BD.getConnection();
            PreparedStatement ps=connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                //vamos a ir colocando cada odontologo en la lista
                odontologo=new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4));
                listaOdontologos.add(odontologo);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarXString(String valor) {
        Connection connection= null;
        Odontologo odontologo= null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps= connection.prepareStatement(SQL_BUSCAR_NOMBRE);
            ps.setString(1,valor);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                //ID, matricula, nombre, apellido
                odontologo= new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                connection.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
        return odontologo;
    }
}














