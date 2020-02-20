/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Joke;

/**
 *
 * @author jenso
 */
public class JokeDTO {
    private String jokeText;
    private String type;
    private long jokeID;

    public JokeDTO(String jokeText, String type, long jokeID) {
        this.jokeText = jokeText;
        this.type = type;
        this.jokeID = jokeID;
    }

    public JokeDTO(Joke joke) {
        this.jokeText = joke.getJokeText();
        this.type = joke.getType();
        this.jokeID = joke.getId();

    }
}
