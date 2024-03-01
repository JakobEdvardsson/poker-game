package org.pokergame.server;


import org.pokergame.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public final class ServerController {
    private volatile ArrayList<Lobby> lobbies;
    private static ServerController serverController;
    private HashMap<ClientHandler, String> connectedClients;

    private ServerController() {
        ServerConnection serverConnection = new ServerConnection(1337, this);
        serverConnection.start();

        lobbies = new ArrayList<Lobby>();
        connectedClients = new HashMap<ClientHandler, String>();

        lobbies.add(new Lobby());
        lobbies.add(new Lobby());
        lobbies.add(new Lobby());

    }

    public static ServerController getInstance() {
        if (serverController == null) {
            serverController = new ServerController();
        }

        return serverController;
    }

    public String[][] getLobbies() {

        String[][] lobbyStrings = new String[3][4];

        for (int i = 0; i < 3; i++) {
            Lobby lobby = lobbies.get(i);

            int index = 0;
            for (Player player : lobby.getPlayers()) {
                System.out.println("player found!");
                System.out.println(player.getName());
                lobbyStrings[i][index++] = player.getName();
            }
        }

        return lobbyStrings;
    }

    public synchronized void createLobby() {
        Lobby lobby = new Lobby();
        lobby.setLobbyIndex(lobbies.size());
        lobbies.add(lobby);
    }

    public synchronized Lobby joinLobby(ClientHandler handler, int lobbyIndex) {
        Lobby lobby = lobbies.get(lobbyIndex);
        String userName = connectedClients.get(handler);

        if (lobby.getAvailable()) {
            lobby.addPlayer(userName, handler);
            updateLobbyStatus();
            return lobby;
        }

        return null;
    }

    public synchronized void updateLobbyStatus() {
        String[][] lobbies = getLobbies();

        for (ClientHandler handler : connectedClients.keySet()) {
            handler.pushLobbyInformation(lobbies);
        }
    }

    public synchronized Lobby leaveLobby(ClientHandler handler, int lobbyIndex) {
        Lobby lobby = lobbies.get(lobbyIndex);
        String userName = connectedClients.get(handler);
        lobby.removePlayer(userName);
        updateLobbyStatus();

        return lobby;
    }

    /**
     * Registers client to the server
     * @param userName Username of the connected client
     * @param handler Handler to communicate with the client
     * @return true if the client was successfully registered, false otherwise
     */
    public boolean registerClient(String userName, ClientHandler handler) {
        if (connectedClients.containsKey(userName)) return false;
        connectedClients.put(handler, userName);
        return true;
    }
}
