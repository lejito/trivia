package com.adaptionsoft.games.trivia;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.adaptionsoft.games.uglytrivia.Game;


import static org.junit.jupiter.api.Assertions.*;


class GameTest {


    private Game game; // Se tiene una referencia para la instancia del juego


    @BeforeEach 
    void setUp() { // Se inicializa el juego antes de cada prueba
        game = new Game();
    }

    @Test
    void testHowManyPlayers() {
        assertEquals(0, game.howManyPlayers());
        game.add("Player 1");
        assertEquals(1, game.howManyPlayers());
        game.add("Player 2");
        assertEquals(2, game.howManyPlayers());
    }
    @Test
    void testAddPlayer() { // prueba unitaria que verifica que se pueda agregar un jugador al juego
        assertTrue(game.add("Player 1"));
        assertTrue(game.add("Player 2"));
        assertEquals(2, game.howManyPlayers());
    }

    
    @Test
    void testIsNotPlayable() { // prueba unitaria que verifica que el juego no es jugable con 1 jugador
        assertFalse(game.isPlayable());
        game.add("Player 1");
        assertFalse(game.isPlayable());
    }

    @Test
    void testIsPlayable() { // prueba unitaria que verifica si el juego es jugable con 2 jugadores
        assertFalse(game.isPlayable());
        game.add("Player 1");
        game.add("Player 2");
        assertTrue(game.isPlayable());
    }

    
    @Test
    void testGameStartWithPlace0() { // prueba unitaria que verifica si el juego empieza con los jugadores en la posición 0
        game.add("Player 1");
        game.add("Player 2");
        assertEquals(0, game.getPlayerPlace(0)); // Player 1 comienza en la posición 0
        assertEquals(0, game.getPlayerPlace(1)); // Player 2 comienza en la posición 0
    }
    
    @Test
    void testGameStartWith0Coins() { // prueba unitaria que verifica si el juego comienza con los jugadores con 0 monedas
        game.add("Player 1");
        game.add("Player 2");
        assertEquals(0, game.getPlayerCoins(0)); // Player 1 comienza con 0 monedas
        assertEquals(0, game.getPlayerCoins(1)); // Player 2 comienza con 0 monedas
    }
    @Test// Roll solo mueve a los jugadores entre 0 a 11, si el valor es mayor a 11, se mueve a la posición en circuito
    void testRollWith3MovesTo3() {// prueba unitaria que verifica que el jugador se mueve a la posición correcta al lanzar el dado con un valor menor a 11
        game.add("Player 1");
        game.add("Player 2");
        game.roll(3);
        assertEquals(3, game.getPlayerPlace(0)); // Player 1 se mueve a la posición 3
    }

    @Test
    void testRollWith13MovesTo1() {
        game.add("Player 1");
        game.add("Player 2");

        game.roll(13);
        assertEquals(1, game.getPlayerPlace(0)); // Player 1 se mueve a la posición 3
    }

    
    @Test
    void testRollWith12MovesTo0() {
        game.add("Player 1");
        game.add("Player 2");

        game.roll(12);
        assertEquals(0, game.getPlayerPlace(0)); // Player 1 se mueve a la posición 3
    }

    @Test
    void testWasCorrectlyAnswered() {
        game.add("Player 1");
        game.add("Player 2");

        game.roll(2);
        assertTrue(game.wasCorrectlyAnswered());
        assertEquals(1, game.getPlayerCoins(0)); // Player 1 earns 1 coin

        game.roll(3);
        assertTrue(game.wasCorrectlyAnswered());
        assertEquals(1, game.getPlayerCoins(1)); // Player 2 earns 1 coin
    }


    @Test
    void testGameIsWinable() {
        game.add("Player 1");
        game.add("Player 2");

        int currentPlayer = 0; // Player 1
        boolean notAWinner = true;
        
        do{
            game.roll(2); 
            if(currentPlayer == 0) {
                notAWinner = !game.wasCorrectlyAnswered(); // Player 1 answers correctly
                currentPlayer = 1; // Switch to Player 2
            } else {
                game.wrongAnswer(); // Player 2 answers wrongly
                currentPlayer = 0; // Switch to Player 1
            }
        }
        while(notAWinner); 

        assertFalse(notAWinner); // Se espera que el juego tenga ganadores
        
        // el primer jugador gana el juego
        assertEquals(6, game.getPlayerCoins(0)); // Player 1 earns 6 coins
    
    }
    
}



