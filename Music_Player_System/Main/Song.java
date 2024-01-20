package Main;

import java.util.*;
import java.util.Scanner;

class Node{
    String song ;
    Node previous ;
    Node next;
}
public class Song {

    Node top , temp , top1;
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

        Node start , hold;
        int number = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("select a playlist you want to add the songs to :");
        for(Node node : playlists){
            System.out.println("- " + node.song);
        }

        String choice = scanner.nextLine();

        start = playlists.stream().filter(node -> node.song.equalsIgnoreCase(choice))
                .findFirst().orElse(null);
        hold = start;
        create();

        do{
            System.out.println("\n1.Add  New Song\n2.Delete Song\n3.Display Entered Playlist\n4.Total Songs\n5.Search Song\n6.Play Song\n7.Recently Played List\n8.Last Played\n9. Sorted playlist\n10.Add From File\n11.Exit");
            System.out.print("\nEnter your choice- ");
            number = scanner.nextInt();
            switch (number) {
                case 1:
                    addNode(start);
                    break;

                case 2:
                    deleteMenu(start);
                    break;

                case 3:
                    printList(start);
                    break;

                case 4:
                    countNodes(hold);
                    break;

                case 5:
                    search(start);
                    break;

                case 6:
                    play(start);
                    break;

                case 7:
                    recent();
                    break;

                case 8:
                    topElement();
                    break;

                case 9:
                    sort(start.next);
                    printList(start);
                    break;

                case 10:
                    //addPlaylist(start);
                    break;

                case 11:
                    System.exit(0);
            }

        }while(number != 11);


    }
    private  void create() {
        top = null;
    }

    private void addNode(Node first){
        String s;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter Song name-  ");
        s= scanner.nextLine();

        while(first.next != null){
            first = first.next;
        }
        first.next = new Node();

        first.previous = first;
        first = first.next;
        first.song = s;
        first.next = null;

    }
    private void deleteMenu(Node first){
        int c;
        System.out.println("Which type of delete do you want?\n1.By Search\n2.By Position");
        Scanner scanner = new Scanner(System.in);
        c = scanner.nextInt();

        switch(c){
            case 1:
                delSearch(first);
                break;

            case 2:
                int pos;
                System.out.print("\nEnter the pos of the song : ");
                pos = scanner.nextInt();
                delPos(first, pos - 1);
                break;

        }
    }

    private void delSearch(Node first){

        String song;
        printList(first);
        System.out.print("\nChoose song you wish to delete- ");
        Scanner scanner = new Scanner(System.in);
        song = scanner.nextLine();
        int flag = 0;

        while(first != null){

            if(first.song.equalsIgnoreCase(song)){

                System.out.println("\n#Song Found");
                Node temp = first;
                temp.previous.next = first.next;
                temp.next.previous = temp.previous;
                flag++;
                break;
            }else{
                first = first.next;
            }
        }
        if (flag == 0) {
            System.out.println("\n#Song Not found");
        }

    }
    private  void printList(Node first) {
        System.out.print("\nPlaylist Name- ");
        while (first.next != null) {
            System.out.println(first.song);
            first = first.next;
        }
        System.out.println(first.song);
    }

    private Node delPos(Node first , int pos){

        Node n1 , prev1 , temp;
        prev1 = new Node();
        temp = new Node();

        int i= 0 ;

        if(pos == 0){
            temp = first;
            first = first.next;
            first.previous = null;
            System.out.println("\nThe list is updated\nUse the display function to check\n");
            return first;

        }
        while(i < pos-1){
            prev1 = first;
            first = first.next;
            i++;
        }
        if (first.next == null) {
            temp = first;
            prev1.next.previous = null;
            prev1.next = null;
            System.out.println("\nThe list is updated\nUse the display function to check\n");
        } else {
            temp = first;
            prev1.next = temp.next;
            temp.next.previous = prev1;
            System.out.println("\nThe list is updated\nUse the display function to check\n");
        }
        return first;
    }
    private  void countNodes(Node first) {
        int i = 0;
        while (first.next != null) {
            first = first.next;
            i++;
        }
        i++;
        System.out.println("\nTotal songs-  " + (i - 1));
    }

    private  void search(Node first) {
        String song;
        System.out.print("\nEnter song To be Searched- ");
        Scanner scanner = new Scanner(System.in);
        song = scanner.nextLine();
        int flag = 0;

        while (first != null) {
            if (first.song.equals(song)) {
                System.out.println("\n#Song Found");
                flag++;
                break;
            } else {
                first = first.next;
            }
        }

        if (flag == 0) {
            System.out.println("\n#Song Not found");
        }
    }

    private void play(Node first) {
        String song;
        System.out.print("\nChoose song you wish to play- ");
        Scanner scanner = new Scanner(System.in);
        song = scanner.nextLine();
        int flag = 0;

        while (first != null) {
            if (first.song.equals(song)) {
                System.out.println("\n=>Now Playing......" + song);
                flag++;
                push(song);
                break;
            } else {
                first = first.next;
            }
        }

        if (flag == 0) {
            System.out.println("\n#Song Not found");
        }
    }
    private  void push(String data) {
        if (top == null) {

            top = new Node();
            top.next = null;
            top.song = data;

        } else if (!top.song.equals(data)) {

            temp = new Node();
            temp.next = top;
            temp.song = data;
            top = temp;

        }
    }
    private  void recent() {
        display();
    }
    private  void display() {
        top1 = top;
        if (top1 == null) {
            System.out.println("\n=>NO recently played tracks.");
            return;
        }

        System.out.println("\n#Recently played tracks-");
        while (top1 != null) {
            System.out.println(top1.song);
            top1 = top1.next;
        }
    }
    private  void topElement() {
        top1 = top;
        if (top1 == null) {
            System.out.println("\n#NO last played tracks.");
            return;
        }
        System.out.println("\n=>Last Played Song - " + top.song);
    }
    private  void sort(Node pointer) {
        Node a, b, c, e, tmp;
        e = null;

        while (e != pointer.next) {
            c = a = pointer;
            b = a.next;

            while (a != e) {
                if (a.song.compareTo(b.song) > 0) {
                    if (a == pointer) {
                        tmp = b.next;
                        b.next = a;
                        a.next = tmp;
                        pointer = b;
                        c = b;
                    } else {
                        tmp = b.next;
                        b.next = a;
                        a.next = tmp;
                        c.next = b;
                        c = b;
                    }
                } else {
                    c = a;
                    a = a.next;
                }
                b = a.next;
                if (b == e) {
                    e = a;
                }
            }
        }
    }



}
