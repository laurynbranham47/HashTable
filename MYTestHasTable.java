
public class MYTestHasTable{
	
	public static void main(String args[]) {
		HashTable hash = new HashTable<String, Integer>();
		
		//TEST LOAD FACTOR
		System.out.println("INSERT!!");
		for(int i = 0; i < 1000; ++i) {
			hash.put(""+i, i);
		}
		
		System.out.println();
		System.out.println("SIZE: " + hash.size());
		System.out.println();
		
		System.out.println("GET!!");
		for(int i = 0; i < 1000; ++i) {
			System.out.println("GET: " + i);
			hash.get(""+i);
		}
		
		System.out.println();
		System.out.println("SIZE: " + hash.size());
		System.out.println();
		
		System.out.println("REMOVE!!");
		for(int i = 0; i < 1000; ++i) {
			System.out.println("RM: " + i);
			hash.remove(""+i);
		}
		
		System.out.println("SIZE: " + hash.size());
		
	}
	
}















/**
//TODO:  DELETE!!!!! FOR TESTING ONLY.
public int fullSize() {
	return initialCapacity;
}

//TODO:  DELETE!!!!! FOR TESTING ONLY.
public double getLF() {
	return loadFactor;
}
	
public void printAll() {
		Node temp = null;
		
		for(int i = 0; i < list.size(); ++i) {
			
			temp = list.get(i);
			
			while(temp != null) {

				System.out.println("Key: " + temp.getKey() + " Value: " + temp.getValue() + " Index i: " + i);
				System.out.println(temp.getHash());
				System.out.println("");
				temp = temp.next;
				
			}
		}
	}


else { //

				while(temp.next != null) {
					if(temp.next.getKey().equals(key)) {
						 if(temp.next.next != null) {
							 temp.next = temp.next.next;
						 } else {
							 temp.next = null;
						 }
						--loadFactor;
					} else {
						temp = temp.next;
					}	
				}
			}


*/