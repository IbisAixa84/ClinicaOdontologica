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
    private static final org.apache.log4j.Logger LOGGER= Logger.getLogger(OdontologoDAOH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Guardo un odontologo");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement ps_insert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps_insert.setString(1, odontologo.getNombre());
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
        return null;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

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
        return null;
    }
}
