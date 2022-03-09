/******************************************************************************
 *  
 *  Symbol table implementation with sequential search in an
 *  unordered linked list of key-value pairs.
 *  
 *  As a multimap, multiple pairs with the same key are possible.
 *  Although a <key, value> pair cannot be repeated. 
 *
 ******************************************************************************/

/**
 *  The {@code LinkedSymbolTable} class represents an (unordered)
 *  symbol table of generic key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides a <em>keys</em> method for iterating over all of the keys.
 *  <p>
 *  It relies on the {@code equals()} method to test whether two keys
 *  are equal. It does not call either the {@code compareTo()} or
 *  {@code hashCode()} method. 
 *  <p>
 *  This implementation uses a <em>singly linked list</em> and
 *  <em>sequential search</em>.
 *  The <em>put</em> and <em>delete</em> operations take &Theta;(<em>n</em>).
 *  The <em>get</em> and <em>contains</em> operations takes &Theta;(<em>n</em>)
 *  time in the worst case.
 *  The <em>size</em>, and <em>is-empty</em> operations take &Theta;(1) time.
 *  Construction takes &Theta;(1) time.
 *  <p>
 *  For additional documentation, see
 *  <a href="https://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  
 *  Modified:
 *  @author C. Alvin
 *  3/5/21
 */

package hashing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedMultiMap<Key, Value> implements MultiMap<Key, Value>
{
    private int  _size;    // number of key-value pairs
    private Node _first;   // the linked list of key-value pairs

    // a helper linked list data type
    private class Node
    {
        private Key _key;
        private Value _value;
        private Node _next;

        public Node(Key key, Value val, Node next)
        {
            this._key  = key;
            this._value  = val;
            this._next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public LinkedMultiMap() { }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() { return _size; }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() { return size() == 0; }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to containsKey() is null"); 

        //
		// TODO
		//

        return false;
     }

    /**
     * Returns the value associated with the given key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null} or
     *                                  {@code value} is {@code null}
     */
    public boolean containsPair(Key key, Value value)
    {
        if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
        if (value == null) throw new IllegalArgumentException("argument to get() is null"); 

        //
		// TODO
		//

        return false;
    }

    /**
     * Inserts the specified key-value pair into the symbol table if is it not
     * currently in the list
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null} or
                                        {@code value} is {@code null}
     */
    public void put(Key key, Value val)
    {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 
        if (val == null) throw new IllegalArgumentException("second argument to put() is null"); 

        //
		// TODO: avoid duplication
		//
		
        _first = new Node(key, val, _first);
        _size++;
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param key -- the key
     * @param val -- the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key, Value val)
    {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 
        if (val == null) throw new IllegalArgumentException("second argument to put() is null"); 

        _first = delete(_first, key, val);
    }

    // delete <key, value> in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key, Value val)
    {
        if (x == null) return null;

        if (key.equals(x._key) && val.equals(x._value))
        {
            _size--;
            return x._next;
        }

        x._next = delete(x._next, key, val);
        return x;
    }
    
    /**
     * Removes all pairs containing the specified key from this symbol table     
     * (if the key is in this symbol table).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void deleteAll(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to deleteAll() is null"); 

        _first = deleteAll(_first, key);
    }
    
    // delete <key, value> in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node deleteAll(Node x, Key key)
    {
        //
		// TODO
		//
    }
   
    /**
     * Returns all unique keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in the symbol table
     */
    public Iterable<Key> keySet()
    {
        //
		// TODO
		//
    }
    
    /**
     * Returns all Values in the symbol table as an {@code Iterable} given a
     * {@code key}. To iterate over all of the keys in the symbol table named
     * {@code st}, use the foreach notation:
     *             {@code for (Value value : st.getAllValues())}.
     *
     * @return all values corresponding to a key in the symbol table
     */
    public Iterable<Value> getAll(Key key)
    {
        Queue<Value> queue = new LinkedList<Value>();

        //
		// TODO
		//

        return queue;
    }
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (Node x = _first; x != null; x = x._next)
        {
        	sb.append("(" + x._key.toString() + ", " + x._value.toString() + ") ");
        }

        return sb.toString();
	}
}