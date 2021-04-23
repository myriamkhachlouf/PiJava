/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Emplois;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 21624
 */
public interface IserviceEmploi {
public void AddEmploi(Emplois e) throws SQLException;
public List<Emplois> AfficherEmplois() throws SQLException;
public void ModifierEmploi(int id, Emplois e)throws SQLException ;
}
