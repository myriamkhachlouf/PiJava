/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Offres;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 21624
 */
public interface IserviceOffre {
public void AddOffre(Offres a) throws SQLException;
public List<Offres> AfficherOffre() throws SQLException;
public void ModifierOffre(int id, Offres a)throws SQLException ;
public boolean rate(Offres p)throws SQLException;
}
