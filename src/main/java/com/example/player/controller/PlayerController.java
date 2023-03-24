/*
 * 
 * You can use the following import statemets
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 * 
 */
package com.example.player.controller;
  
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;
 import com.example.player.service.PlayerH2Service;
 import com.example.player.model.*;
  import java.util.*;
  
  @RestController
  public class PlayerController{

  @Autowired
  public PlayerH2Service ps;

    @GetMapping("/players")
    public ArrayList<Player> getPlayers(){
      return ps.getPlayers();
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player){
      return ps.addPlayer(player);
    }

    @GetMapping("/players/{playerId}")
    public Player getPlayer(@PathVariable("playerId") int playerId){
      return ps.getPlayer(playerId);
    }
    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable("playerId") int playerId, @RequestBody Player player){
      return ps.updatePlayer(playerId, player);
    }

    @DeleteMapping("/players/{playerId}")
    public void deletePlayer(@PathVariable ("playerId") int playerId){
      ps.deletePlayer(playerId);
    }
  }