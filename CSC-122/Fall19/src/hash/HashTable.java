package hash;
/**
 * Hash table class
 * @author Boone Tison
 * Date: 11/11/2019
 */

import java.util.ArrayList;
import adt.LinkedList;

public class HashTable<E> {
	  private ArrayList<LinkedList<E>> table;
      private int length; // number of possible entries
      private int size; // actual entries in array
      private final int DEFAULT_SIZE = 31;
      
      /**
       * Default constructor
       */
      public HashTable() {
              table = new ArrayList<LinkedList<E>>(DEFAULT_SIZE);
              for (int i=0; i<DEFAULT_SIZE; i++)
                      table.add(i, new LinkedList<E>() );
              length = DEFAULT_SIZE;
              size = 0;
      }
      
      /**
       * @return length
       */
      public int getLength() {
    	  return length;
      }
      
      /**
       * @return size
       */
      public int getSize() {
    	  return size;
      }
      
      /**
       * @param item
       * @return hash code
       * Should be overwritten
       */
      public int getHashCode(E item) {
    	  return 0;
    	  // Should be overwritten by individual object class
      }
      
      /**
       * Add item to linked list
       * Utilizes separate chaining
       * If load factor is over 70%, doubles the size
       * @param item
       */
      public void add(E item) {
    	  int n = getHashCode(item);
    	  table.get(n).add(item);
    	  size++;
    	  if (loadFactor() > 0.7) {
    		  ArrayList<LinkedList<E>> pc = new ArrayList<LinkedList<E>>(length*2);
    		  for (int i = 0; i < length; i++) 
    			  pc.add(i, table.get(i));
    		  length = length*2;
    		  for (int i = length/2; i < length; i++)
    			  pc.add(i, new LinkedList<E>() );
    		  table = pc;
    	  }	  
      }
      
      /**
       * @param item
       * @return item from array
       */
      public E remove(E item) {
    	  int n = getHashCode(item);
    	  return table.get(n).remove(0);
      }
      
      /**
       * @param item
       * @return boolean if list contains item
       */
      public boolean contains(E item) {
    	  int n = getHashCode(item);
    	  return table.get(n).contains(item);
      }
      
      /**
       * @return boolean if array is empty
       */
      public boolean isEmpty() {
    	  return (table.size() == 0);
      }
      
      /**
       * @return percent of length in use
       */
      public double loadFactor() {
    	  return (size / (double)length);
      }
}
