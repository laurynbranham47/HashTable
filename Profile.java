/**
 * Filename:   Profile.java
 * Project:    p3
 * Authors:    Lauryn Branham Lecture 002
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * 
 * Due Date:   October 29th 10PM
 * Version:    1.0
 * 
 * Credits:    None
 * 
 * Bugs:       No known bugs
 */
import java.util.TreeMap;

/**
 * This class tests tree map against hash table.
 * @author lauryn
 *
 * @param <K>
 * @param <V>
 */

public class Profile<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;
	
	public Profile() {
		// TODO: complete the Profile constructor
		// Instantiate hashtable and treemap
		hashtable = new HashTable<K, V>();
		treemap = new TreeMap<K,V>();
		
	}
	
	public void insert(K key, V value) {
		// TODO: complete insert method
		// Insert K, V into both hashtable and treemap
		hashtable.put(key, value);
		treemap.put(key, value);
	}
	
	public void retrieve(K key) {
		// TODO: complete the retrieve method
		// get value V for key K from both hashtable and treemap
		hashtable.get(key);
		treemap.get(key);
	}
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Expected 1 argument: <num_elements>");
			System.exit(1);
		}
		int numElements = Integer.parseInt(args[0]);
		
		Profile<Integer, Integer> profile = new Profile<Integer, Integer>();
		
		for(int i = 0; i < numElements;  ++i) {
			profile.insert(i, i);
		}
		
		for(int i = 0; i < numElements;  ++i) {
			profile.retrieve(i);
		}
		/*
		 * TODO: complete the main method. 
		 * Create a profile object. 
		 * For example, Profile<Integer, Integer> profile = new Profile<Integer, Integer>();
		 * execute the insert method of profile as many times as numElements
		 * execute the retrieve method of profile as many times as numElements
		 * See, ProfileSample.java for example.
		 */
		
		String msg = String.format("Successfully inserted and retreived %d elements into the hash table and treemap", numElements);
		System.out.println(msg);
	}
}
