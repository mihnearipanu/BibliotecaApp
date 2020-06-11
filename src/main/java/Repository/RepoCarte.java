package Repository;

import Domeniu.Carte;
import Repository.Utilitati.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class RepoCarte {
    public RepoCarte(){

    }

    public void save(Carte c){
        try{
            Connection connection = JDBCUtils.connect();
            String insert = "insert into carti values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, c.getCod());
            preparedStatement.setString(2, c.getTitlu());
            preparedStatement.setString(3, c.getAutor());
            preparedStatement.setString(4, c.getStatus());
            int result = preparedStatement.executeUpdate();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(String cod) {
        try {
            Connection connection = JDBCUtils.connect();
            String delete = "delete from carti where cod = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, cod);
            int result = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String status, Carte c){
        try{
            Connection connection = JDBCUtils.connect();
            String update = "update carti set status = '" + status + "' where cod = '" + c.getCod() + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
            connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public Carte findOne(String cod){
        try{
            Connection connection = JDBCUtils.connect();
            String find = "select * from carti where cod = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            preparedStatement.setString(1, cod);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String titlu = resultSet.getString(2);
                String autor = resultSet.getString(3);
                String status = resultSet.getString(4);
                connection.close();
                resultSet.close();
                return new Carte(cod, titlu, autor, status);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Carte> findAll(){
        try{
            Connection connection = JDBCUtils.connect();
            String select = "select * from carti";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            HashMap<String, Carte> map = new HashMap<>();
            while(resultSet.next()){
                Carte carte = new Carte(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                map.put(carte.getCod(), carte);
            }
            connection.close();
            return new ArrayList<>(map.values());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Carte> findAllAvailable(){
        try{
            Connection connection = JDBCUtils.connect();
            String select = "select * from carti where status = 'in stoc'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            HashMap<String, Carte> map = new HashMap<>();
            while(resultSet.next()){
                Carte carte = new Carte(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                map.put(carte.getCod(), carte);
            }
            connection.close();
            return new ArrayList<>(map.values());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Carte> findAllUnavailable(){
        try{
            Connection connection = JDBCUtils.connect();
            String select = "select * from carti";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            HashMap<String, Carte> map = new HashMap<>();
            while(resultSet.next()){
                Carte carte = new Carte(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                if(!carte.getStatus().equals("in stoc")) {
                    map.put(carte.getCod(), carte);
                }
            }
            connection.close();
            return new ArrayList<>(map.values());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
