/**
 * Filename:   HashTable.java
 * Project:    p3
 * Authors:    Lauryn Branham Lecture 2
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   Oct 19th
 * Version:    1.0
 * 
 * Credits:    None
 * 
 * Bugs:       No known bugs
 */


import java.util.ArrayList;
import java.util.NoSuchElementException;
     
/**
 * This class is the implementation of HashTableADT.
 * 
 * The hash implementation I used is having an arraylist that stores
 * linked list for the elements - using the bucket system for collision // identify your scheme as open addressing or bucket
 * resolution.															// if buckets: describe data structure for each bucket
 * 
 * The hashing algorithm I decided to use was the absolute value		// explain your hashing algorithm here 
 * of the hashcode % the initial capacity of the arraylist. 
 * 
 * @author lauryn
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	/**
	 * This nested class stores the data on each node
	 * object created. 
	 * 
	 * @author lauryn
	 *
	 */
	class Node {
		
		private K key; //stores the key of the node
		private V value; //stores the value associated with the key
		private Node next; //the next node associated with the index
		private int hash; //the hashcode of the node
		
		/*
		 * This constructor sets the key and value pair for the node
		 * along with the hash value of the node.
		 */
		 public Node(K key, V value) {
			 this.key = key; // set key to key given
			 this.value = value; //set value to value given
			 hash = Math.abs(key.hashCode() % initialCapacity);	//set hash 
		 }
		 
		 ///*** GETTERS AND SETTERS ***\\\
		 public K getKey() { //get key of node
			 return key;
		 }
		 public V getValue() { //get value of node
			 return value;
		 }
		 public int getHash() { //get hash index of node
			 return hash;
		 }
		 public void setKey(K key) { //set the key of the node
			 this.key = key;
		 }
		 public void setValue(V value) { //set the value of the node
			 this.value = value;
		 }
		 public void setHash(int hash) { //set the hash of the node
			 this.hash = hash;
		 }
		 
		 
		 
		 
	}

	//Field Members
	private ArrayList<Node> list; //arraylist is the structure of this hashtable - it holds all the contents
	private int initialCapacity; //the capacity of the arraylist
	private double loadFactor = 0; //how many elements are currently stored in the arraylist
	private final static int defaultCap = 100; //default capacity when none is provided
	private final static int defaultLF = 0; //default Load Factor when none is provided
		
	/**
	 * This default constructor calls a non default constructor to set hash table values.
	 */
	public HashTable() {
		this(defaultCap, defaultLF);
	}
	
	/**
	 * This constructor sets the initial capacity, the load factor, and creates/initializes
	 * a new arraylist. 
	 * 
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public HashTable(int initialCapacity, double loadFactor) {
		
		if(initialCapacity % 2 != 1) { //makes the initial capacity an odd number (fake prime)
			initialCapacity += 1;
		}
		
		this.initialCapacity = initialCapacity; //sets initial capacity to given initial capacity
		this.loadFactor = loadFactor; //sets load factor to given load factor 
		
		list = new ArrayList<Node>(); //create new arraylist
		
		for(int i = 0; i < initialCapacity; ++i) { //initialize new array list with given capacity
			list.add(null);
		}
	}

	/**
	 * This method inserts a key and its value into the hash table.
	 */
	@Override
	public void put(K key, V value) throws IllegalArgumentException {
		Node node = new Node(key, value);
		
		if(key == null) { //if key is null, throw an exception
			throw new IllegalArgumentException();
		}		

		//check is arraylist is full, if full --> create new list
		if( loadFactor >= ((double) initialCapacity * 0.75)) {
			newList(list);
		}
		
		//find hash for the new node
		int hash = Math.abs(key.hashCode() % initialCapacity);
		
		//if the location of the hash index is null, place new node there
		if(list.get(hash) == null) {
			list.set(hash, node);
			
		} else { //if location is not null, check until the next link is null and place new node there
			Node temp = list.get(hash);
            while(temp.next != null) {
                temp = temp.next; //if not null, change temp to the next element in the linked list
            }
            temp.next = node;
		}

		++loadFactor; //increase loadFactor
		
	}
	
	/**
	 * This method finds a key in the hash table and returns its value.
	 */
	@Override
	public V get(K key) throws IllegalArgumentException, NoSuchElementException {
		int hashC = Math.abs(key.hashCode() % initialCapacity); //stores hash for key

		//if location found does not equal null and the key given is not null
		if(list.get(hashC) != null && key != null) {
			Node temp = list.get(hashC); //set temp node to node found at the location
			
			//while the current node is not null
			while(temp != null) {
				//if the temp node's key equals key
				if(temp.getKey().equals(key)) {
					//return value associated with the key
					return temp.getValue();
				//if key not found, change to next node in linked list
				} else {
					temp = temp.next;
				}	
			} 
		} 
		if(key == null) { //if element was null, throw exception
			throw new IllegalArgumentException();
		} else { //if element not found, throw exception
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * This method removes the specified key and its value from the hash table.
	 */
	@Override
	public void remove(K key) throws IllegalArgumentException, NoSuchElementException {
		int hash = Math.abs(key.hashCode() % initialCapacity); //find key's hash
		
		//if location found does not equal null and the key given is not null
		if(list.get(hash) != null && key != null) {  
			
			//set node found at the hash index location to the temp node
			Node temp = list.get(hash);	
			
			if(temp.getKey().equals(key)) { //if the key found in the temp node equals the key given
				
				if(temp.next != null) { //if the next node in the linked list is not null
					//set the next node to temp
					temp = temp.next;
					list.set(hash, temp); //reset the hash
				} else {
					temp = null; // can't make it null, have to make it to the next .next
				}
			}
			--loadFactor; //decrease load factor
		}
		else {
			if(key == null) { //if element was null, throw exception
				throw new IllegalArgumentException();
			} else { //if element not found, throw exception
				throw new NoSuchElementException();
			}
		}
	}
	
	/**
	 * This method returns how many elements are currently within the structure.
	 */
	@Override
	public int size() {
		return (int) loadFactor;
	}
	
	/**
	 * This method creates a new list double the size of the original list. 
	 * @param original
	 */
	private void newList(ArrayList<Node> original) {
		
		//create a new arraylist
		ArrayList<Node> newArr = new ArrayList<Node>();
		
		//set a new capacity
		int newCap = initialCapacity * 2;
		
		//set to fake prime
		if(newCap % 2 != 1) {
			newCap += 1;
		}
		
		//initialize new array with new capacity
		for(int i = 0 ; i < newCap; ++i) {
			newArr.add(null);
		}
		
		//set new array list to be original list
		list = rehash(original, newArr, newCap); //do I need = list?
		
		initialCapacity = newCap; //set initial capacity to new capacity
	
	}
	
	/**
	 * This method rehashes elements from the original list to the new list.
	 * 
	 * @param original
	 * @param newArr
	 * @return new list
	 */
	private ArrayList<Node> rehash(ArrayList<Node> original, ArrayList<Node> newArr, int newCap){
		int hash = 0; //stores hash
		Node currentNode; //stores current node being rehashed
		Node temp; //stores temp node being used to store current location in search
		Node newNode; //this node becomes the new node with the same key and value as the old node
		
		for(int i = 0; i < original.size(); ++i) { 
			
			//node we need to rehash
			currentNode = original.get(i);
			
			//while current node isnt null
			while(currentNode != null) {

				//get new hash
				hash = Math.abs(currentNode.getKey().hashCode() % newCap);
				//re setting the new hash
				currentNode.setHash(hash);
				
				//new array --- element at hash index
				temp = newArr.get(hash);
				
				///you're not checking if the hash index has something there already so instead of setting to .next you're replacing it
				if(temp == null) {
					
					//make new new node for new arraylist with old node's vaues
					newNode = new Node(currentNode.getKey(), currentNode.getValue());
					
					//insert new node into new array
					newArr.set(hash, newNode);
					
				} else {	//if temp isn't null, look for the next null in the linked list			
					while(temp.next != null) {
		                temp = temp.next;
		            }
					
					//make new new node for new arraylist with old node's vaues
					newNode = new Node(currentNode.getKey(), currentNode.getValue());
					
					//set temp.next to new node
					temp.next = newNode;
				}			
				currentNode = currentNode.next;
			}
		}
		
		//return the new array
		return newArr;
	}
}
