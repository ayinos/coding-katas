package problems;

class MyHashSet {

    // Node class for separate chaining (linked list for collision handling)
    private static class ListNode {
        int key;          // Value stored in the set
        ListNode next;    // Pointer to next node in the chain

        ListNode(int key) {
            this.key = key;
        }
    }

    // Array of buckets (each bucket is a linked list)
    private final ListNode[] hashSet;

    // Constructor: Initialize hash table
    public MyHashSet() {

        // Create array of size 10000 (fixed bucket size)
        hashSet = new ListNode[10000];

        // Initialize each bucket with a dummy node
        // Dummy node simplifies add/remove logic (avoids null checks on head)
        for (int i = 0; i < hashSet.length; i++) {
            hashSet[i] = new ListNode(0); // dummy head node
        }
    }

    // Add key to HashSet
    public void add(int key) {

        // Step 1: Calculate bucket index using hash function
        int index = key % hashSet.length;

        // Step 2: Start from dummy head of that bucket
        ListNode curr = hashSet[index];

        // Step 3: Traverse linked list to check if key already exists
        while (curr.next != null) {

            // If key already exists → do nothing (no duplicates allowed)
            if (curr.next.key == key)
                return;

            curr = curr.next;
        }

        // Step 4: If key not found → insert at end
        curr.next = new ListNode(key);
    }

    // Remove key from HashSet
    public void remove(int key) {

        // Step 1: Calculate bucket index
        int index = key % hashSet.length;

        // Step 2: Start from dummy head
        ListNode curr = hashSet[index];

        // Step 3: Traverse list to find node BEFORE the one to delete
        while (curr.next != null) {

            // If next node contains the key → remove it
            if (curr.next.key == key) {

                // Bypass the node (delete it)
                curr.next = curr.next.next;
                return;
            }

            curr = curr.next;
        }
    }

    // Check if key exists in HashSet
    public boolean contains(int key) {

        // Step 1: Calculate bucket index
        int index = key % hashSet.length;

        // Step 2: Start from dummy head
        ListNode curr = hashSet[index];

        // Step 3: Traverse list to search for key
        while (curr.next != null) {

            if (curr.next.key == key)
                return true;

            curr = curr.next;
        }

        // If not found
        return false;
    }
}

/*
Time Complexity:
- Average: O(1)
- Worst case (many collisions): O(n)

Space Complexity:
- O(n) for storing elements
*/