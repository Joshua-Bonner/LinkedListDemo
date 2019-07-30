package linkedlists;
import java.util.*;

public class LinkedLists {

    // Driver
    public static void main(String[] args) {
        Linkedlist list = new Linkedlist();
        Random rand = new Random();
       
        // Fil list with integers 1-10
        /*for (int i=10; i>0; i--)
            list.append(i);*/
        
        // Fill list with random integers
        for (int i=0; i<20; i++){
            int n = rand.nextInt(100);
            list.append(n);
        }
        
        // Remove duplicate entries
        list.removeDups(list);
        
        
        // Print the list unsorted
        System.out.println("Unsorted Linkedlist - ");
        System.out.print(list.toString());
        
        // Head of list
        Node h = list.head;
        System.out.println("\nHead node : " +h.data);
        
        // Mid of list
        Node midNode = list.getMid(list.head);
        System.out.println("Middle node: " +midNode.data);
        
        // Last of list
        Node lastNode = list.lastNode(list.head);
        System.out.println("Last node: " +lastNode.data);
        
        // Sort list and print
        System.out.println("\nSorted Linkedlist - ");
        list.head = list.mergeSort(list.head);
        System.out.print(list.toString());
        
        // New head of list
        System.out.println("\nHead node : " +list.head.data);
        
        // New mid of list
        Node newMid = list.getMid(list.head);
        System.out.println("Middle node: " +newMid.data);
        
        // New last of list
        Node newLast = list.lastNode(list.head);
        System.out.println("Last node: " +newLast.data);
        
        System.out.println("\n");
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
        
        public Node mergeSort(Node h){
            if (h == null || h.next == null) return h;
            Node mid = getMid(h);
            Node nextOfMid = mid.next;
            mid.next = null;
            
            Node left = mergeSort(h);
            Node right = mergeSort(nextOfMid);
            
            Node sortedList = sortedMerge(left, right);
            return sortedList;    
        }
        
        public Node sortedMerge(Node a, Node b){
            Node result = null;
            if (a == null) return b;
            if (b == null) return a;
            
            if (a.data <= b.data) {
                result = a;
                result.next = sortedMerge(a.next, b);
            }
            else{
                result = b;
                result.next = sortedMerge(a, b.next);
            }
            return result;
        }
        
        public Node getMid(Node h){
            if (h == null) return h;
            Node slowPtr = h;
            Node fastPtr = h.next;
            
            while(fastPtr != null){
                fastPtr = fastPtr.next;
                if (fastPtr != null){
                    slowPtr = slowPtr.next;
                    fastPtr = fastPtr.next;
                }
            }
            return slowPtr;
        }
        
        public Node lastNode(Node h){
            while(h.next != null){
                h = h.next;
            }
            return h;
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