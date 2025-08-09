import java.util.HashMap;
// implement the algorithm that are recantly used 


public class Cache
{
    HashMap<Integer , LinkedListNode> map = new HashMap<Integer , LinkedListNode>();

    private LinkedListNode LinkedListhead = null;
    private LinkedListNode LinkedListTail = null;

    private int maxSize;

    public Cache(int capacity)
    {
        this.maxSize = capacity;
    }

    //deleting the node at given location and inserting at LinkedList Head
    public String getValue(int key)
    {
        LinkedListNode item = map.get(key);
        
        if(item == null) return null;

        if(item != LinkedListhead)
        {
            removeFromLinkedList(item);//for deleting the node from linkedlist
            insertAtFrontLinkedList(item);//inserting the node at head
        }
        
        return item.Value;
    }


    public void removeFromLinkedList(LinkedListNode node)
    {
        if(node == null) return;

        if(node.prev != null) node.prev.next = node.next; 
        if(node.next != null) node.next.prev = node.prev; 
        if(node == LinkedListTail) LinkedListTail = node.prev; //if node at tail
        if(node == LinkedListhead) LinkedListhead = node.next; //if node at head


    }

    //inserting the node at head
    public void insertAtFrontLinkedList(LinkedListNode node)
    {
        if(LinkedListhead == null)
        {
            LinkedListhead = node;
            LinkedListTail = node;
            return;
        }
        else{
            node.next = LinkedListhead;
            LinkedListhead.prev = node;
            LinkedListhead = node;

        }
    }


    public boolean removekey(int key)
    {
        LinkedListNode node = map.get(key);

        removeFromLinkedList(node);
        map.remove(key);

        return true;
    }

    public void setKeyValue(int key ,String value)
    {

        removekey(key);

        if(map.size() >= maxSize && LinkedListTail != null)
            removekey(LinkedListTail.key);
        
        LinkedListNode node = new LinkedListNode(key, value);
        insertAtFrontLinkedList(node);
        map.put(key, node);

    }

    public LinkedListNode lecentlyUsed()
    {
        if( LinkedListhead == null) return null;

        return LinkedListhead;
    }

    public static class LinkedListNode {

        private LinkedListNode  next , prev;
        public int key;
        public String Value;


        public LinkedListNode(int key,String value)
        {
            this.key = key;
            this.Value = value;
            this.next = null;
            this.prev = null;
        }

    
        
    }

    public static void main(String[] args)
    {
        Cache cache = new Cache(9);
        cache.setKeyValue(2, "Anurag");
        cache.setKeyValue(3, "Ajit");
        cache.setKeyValue(5, "Abcd");

        String mew_value = cache.getValue(5);
        System.out.println(mew_value);

        mew_value = cache.getValue(3);
        System.out.println(mew_value);

        LinkedListNode recent = cache.lecentlyUsed();
        System.out.println("reacenty Used "+recent.Value);




    }
}