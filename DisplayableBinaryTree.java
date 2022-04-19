

package edu.albany.datastrutures_hw4;

/**
 *
 * @author loganmead
 */
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

/**
   This program demonstrates the graphical
   display of binary trees.
 */

public class DisplayableBinaryTree{
    private class Node{
        int value;          // Value stored in node
        Node left, right;    // Left and right child      
        
        /**
           Constructor for leaf nodes.
           @param val The value to initialize the node.
        */
        
        Node(int val){
            value = val;
            left = null;
            right = null;
        }   
        
        /** 
           Constructor for non-leaf nodes.
           @param val The value to initialize the node.
           @param leftChild The link to the left child.
           @param rightChild The link to the right child.
        */

        Node(int val, Node leftChild, Node rightChild){
            value = val;
            left = leftChild;
            right = rightChild;
        }
    }
    
    /**
       The BTreeDisplay class graphically displays
		 trees in a JPanel. The JPanel is recursively
		 partitioned into a top part dislaying the root,
		 and two lowerparts displaying the left and 
		 right subtrees.
    */
    
    private class BTreeDisplay extends JPanel{
        /**
           Constructor.
           @param tree The root of the binary 
			  tree to display.
         */
        
        BTreeDisplay(Node tree){           
           setBorder(BorderFactory.createEtchedBorder());
           setLayout(new BorderLayout());
           if (tree != null) {          
               String value = String.valueOf(tree.value);  
               int pos = SwingConstants.CENTER;
               JLabel rootLabel = new JLabel(value, pos);                         
               add(rootLabel, BorderLayout.NORTH);
               JPanel panel = new JPanel(new GridLayout(1, 2));
               panel.add(new BTreeDisplay(tree.left));
               panel.add(new BTreeDisplay(tree.right));    
               add(panel);
           }       
        }   
    }
   
    private Node root;          // Root of binary tree
    
    /**
       Constructor.
    */
    
    public DisplayableBinaryTree(int[] array, int start, int size){
        root = ArrayToBinaryTree(array, 0, size - 1);      
    }
    
    /**
       The getView method creates and returns a 
       a graphical view of the binary tree.
       @return A panel that displays a view of the tree.
    */
    
    public JPanel getView(){
       return new BTreeDisplay(root);       
    }

    /**
       The main method simply creates a Displayable
       Binary tree and displays it.
    */
    //NOT FROM TEXTBOOK
     Node ArrayToBinaryTree(int[] array, int start, int end){
      if(start > end){                                                            //error checking to see if the start is greater than end
        return null;                                                              //return null
      }
      int middle = 0;                                                             //creates middle to the middle of the array
      middle = (start + end)/2;
      Node node = new Node(array[middle]);                                        //creates a new node at middle of array
      node.left = ArrayToBinaryTree(array, start, middle - 1);                    //recursion for left node of array
      node.right = ArrayToBinaryTree(array, middle + 1, end);                     //recurion for right node of array
      return node;                                                                //returns the node
    }
    //NOW CONTINUE TEXTBOOK CODE
    public static void main(String [ ] args){
      Scanner scanner = new Scanner(System.in);                                   //creates variables
      Random rand = new Random();
      int size = 0;
      System.out.println("--------------------------");
      System.out.print("Enter a Size for an Array: ");                            //asks for size of array
      size = scanner.nextInt();
      int[] array = new int[size];
      for(int i = 0; i < size; i++){                                              //adds random numbers to array
        array[i] = rand.nextInt(51);                                              //random number from 0-50 
      } 
      Arrays.sort(array); 
        

      //Recursive binary search here
      //Node array = 

      DisplayableBinaryTree binTree = new DisplayableBinaryTree(array, 0, size);
      
       String title = "Graphical Display of Binary Tree";		
       JFrame bFrame = new JFrame(title);                  
       bFrame.add(binTree.getView());
       bFrame.pack();
       bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       bFrame.setVisible(true);
    }
}
