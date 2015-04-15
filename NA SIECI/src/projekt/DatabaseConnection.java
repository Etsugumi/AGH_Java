package projekt;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class DatabaseConnection extends Menu{
    
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  
  public void readDataBase() throws Exception {
    try {
      Class.forName("com.mysql.jdbc.Driver");

      connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/agh?" + "user=root");
      statement = (Statement) connect.createStatement();
    
      /* WPROWADZANIE DO BAZY DANYCH
      preparedStatement = connect.prepareStatement("insert into  users values (?, ?, ?, ?)");
      preparedStatement.setString(1, "ja");
      preparedStatement.setString(2, "haslo");
      preparedStatement.setString(3, "1");
      preparedStatement.setString(4, "0");
      preparedStatement.executeUpdate();
      */

      preparedStatement = (PreparedStatement) connect.prepareStatement("SELECT * from agh.users");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);

      /* USUWANIE Z BAZY DANYCH
      preparedStatement = connect
      .prepareStatement("delete from agh.users where login= ? ; ");
      preparedStatement.setString(1, "ja");
      preparedStatement.executeUpdate();
      */      
    } catch (Exception e) {
      throw e;
    } finally {close();}
  }
  
  public boolean CheckAcc(String name, String pass) throws Exception{
      
      try {
          //ładowanie sterownika
          Class.forName("com.mysql.jdbc.Driver");
          
          connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/agh","root","");
          statement = (Statement) connect.createStatement();
          
          preparedStatement = (PreparedStatement) connect.prepareStatement("Select * from agh.users where login=? and pass=?");
          preparedStatement.setString(1, name);
          preparedStatement.setString(2, pass);
          resultSet = preparedStatement.executeQuery();
          
          if(resultSet.next()){
              
            LoggedIn lobby = new LoggedIn(name);
                    
            lobby.setResizable(false); //blokada zmiany rozmiaru ekranu
            lobby.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //standardowe wyjście z programu
            lobby.setLocationRelativeTo(null); //umieszczenie okna na ekranie
            lobby.setVisible(true);
            
            return true;
            
          }else{
            JOptionPane.showMessageDialog(this, "Wrong login or password", "ERROR!",JOptionPane.ERROR_MESSAGE);
            return false;
          }
          
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
          return false;
      } finally {close();}
  }
  
/*
  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }
*/
  
  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String login = resultSet.getString("login");
      String pass = resultSet.getString("pass");
      String win = resultSet.getString("win");
      String lose = resultSet.getString("lose");
      System.out.println("login: " + login);
      System.out.println("pass: " + pass);
      System.out.println("win: " + win);
      System.out.println("lose: " + lose);
    }
  }

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }
} 
