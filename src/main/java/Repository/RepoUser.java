package Repository;

import Domeniu.Abonat;
import Domeniu.Bibliotecar;
import Domeniu.User;
import Repository.Utilitati.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepoUser {

    public RepoUser(){}

    public Abonat findOneAbonat(String username, String password){
        Connection connection = JDBCUtils.connect();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from abonati where username = ? and password = ?")){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                int cnp = resultSet.getInt("cnp");
                int nrTel = resultSet.getInt("nrTel");
                String nume = resultSet.getString("nume");
                String adresa = resultSet.getString("adresa");
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                preparedStatement.close();
                resultSet.close();
                return new Abonat(nume, adresa, cnp, nrTel, user, pass);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Bibliotecar findOneBibliotecar(String username, String password){
        Connection connection = JDBCUtils.connect();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from bibliotecari where username = ? and password = ?")){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                String nume = resultSet.getString("nume");
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                preparedStatement.close();
                resultSet.close();
                return new Bibliotecar(user, pass, nume);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public User findOne(String username, String password){
        Connection connection = JDBCUtils.connect();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from useri where username = ? and password = ?")){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String rol = resultSet.getString("rol");
                preparedStatement.close();
                resultSet.close();
                return new User(user, pass, rol);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
