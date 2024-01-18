package Main;

import java.util.*;
import java.util.Scanner;

class Node{
    String song ;
    Node previous ;
    Node next;
}
public class Song {

    Set<Node> playlists = new HashSet<>();
    public void CreatePlaylist() {
        System.out.println("\t\t\t**Music Application**");
        System.out.println("Create your playlists : enter your playlistName :");
        System.out.println("Press 1 to exit creating playlist and add songs");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String playListName = scanner.nextLine();

            if (playListName.equals("1")) {
                break;
            } else {
                Node start = new Node();
                start.song = playListName;
                start.next = null;
                playlists.add(start);
            }
        }
        scanner.close();
        createSong(playlists);
    }
    public void createSong(Set<Node> playlists){

        Scanner scanner = new Scanner(System.in);

        int number;
        System.out.println("select a playlist you want to add the songs to :");
        for(Node node : playlists){
            System.out.println("- " + node.song);
        }

        String choice = scanner.nextLine();

        Node start = playlists.stream().filter(node -> node.song.equalsIgnoreCase(choice))
                .findFirst().orElse(null);


    }
}
