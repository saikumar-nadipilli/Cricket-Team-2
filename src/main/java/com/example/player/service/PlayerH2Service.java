/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.player.service;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.jdbc.core.JdbcTemplate;
  import org.springframework.stereotype.Service;
  import org.springframework.web.server.ResponseStatusException;
  import java.util.*;
  import com.example.player.repository.PlayerRepository;
  import com.example.player.model.*;



// Write your code here
@Service
public class PlayerH2Service implements PlayerRepository{

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Player> getPlayers(){
        List<Player> playerList = db.query("SELECT * FROM team",new PlayerRowMapper());
        ArrayList<Player> players = new ArrayList<>(playerList);
        return players;
    }

    @Override
    public Player addPlayer(Player player){
      db.update("INSERT INTO team (playerName,jerseyNumber,role) values(?,?,?)",
      player.getPlayerName(),player.getJerseyNumber(),player.getRole());

      Player newPlayer = db.queryForObject("SELECT * FROM team WHERE playerName = ? AND jerseyNumber = ? AND role = ?", new PlayerRowMapper(),player.getPlayerName(),player.getJerseyNumber(), player.getRole());
      return newPlayer;
    }

    @Override
    public Player getPlayer(int playerId){
      try{
        Player player = db.queryForObject("SELECT * FROM team WHERE playerId = ?", new PlayerRowMapper(),playerId);
         return player;
      }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      
    }

    @Override
    public Player updatePlayer(int playerId,Player player){
      try {
        Player existingPlayer = player ;//db.query("SELECT * FROM team WHERE playerId=?",new PlayerRowMapper(),playerId);
        
      }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      
      if (player.getPlayerName() != null){
        db.update("UPDATE team SET playerName = ? WHERE playerId = ?", player.getPlayerName(),playerId);
      }
      if (player.getJerseyNumber() != 0) {
        db.update("UPDATE team SET jerseyNumber =? WHERE playerId = ?", player.getJerseyNumber(),playerId);
      }
      if (player.getRole() != null){
        db.update("UPDATE team SET role =? WHERE playerId = ?", player.getRole(),playerId);
      }
      return getPlayer(playerId);
    }

    @Override
    public void deletePlayer(int playerId){
      db.update("DELETE FROM team WHERE playerId = ?",playerId);
    }
}