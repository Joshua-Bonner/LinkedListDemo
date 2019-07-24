package linkedlists;
import java.util.*;

public class LinkedLists {

    // Driver
    public static void main(String[] args) {
        Linkedlist list = new Linkedlist();
        
        for (int i=0; i<10; i++)
            list.append(i);
        
        for (int i=0; i<10; i++)
            list.prepend(i);
        
        list.removeDups(list);
        
        System.out.print(list.toString());
        System.out.println();
    }

    public static class Node {
        Node next;
        int data;
        
        public Node(int data){
            this.data = data;
        }
    }
    
    public static class Linkedlist{
        Node head;
        
        public void append(int data){
            if (head == null){
                head = new Node(data);
                return;
            }
            
            Node current = head;
            while (current.next != null)
                current = current.next;
            
            current.next = new Node(data);
        }
        
        public void prepend(int data){
            Node newHead = new Node(data);
            newHead.next = head;
            head = newHead;
        }
        
        public void delete(int data){
            if (head == null) return;
            if (head.data == data){
                head = head.next;
                return;
            }
            
            Node current = head;
            while (current.next != null){
                if (current.next.data == data){
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
        }
        
        public void removeDups(Linkedlist list){
            HashSet set = new HashSet();
            Node previous = null;
            Node current = list.head;
            
            while (current != null){
                if (set.contains(current.data)) previous.next = current.next;
                else {
                    set.add(current.data);
                    previous = current;
                }
                current = current.next;
            }
        }
        
        @Override
        public String toString(){
            String retStr = "Contents: ";
            Node current = head;
            while (current != null){
                retStr += current.data + " ";
                current = current.next;
            }
            return retStr;
        }
    }
}