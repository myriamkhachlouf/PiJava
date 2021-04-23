/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Stage;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 21624
 */
public interface IserviceStage {
public void AddStage(Stage a) throws SQLException;
public List<Stage> AfficherStage() throws SQLException;
public void ModifierStage(int id, Stage a)throws SQLException ;
}
