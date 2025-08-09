public class MyStringBuilder {
    private char[] value;
    private int count;

    // Default constructor
    public MyStringBuilder() {
        value = new char[16];
        count = 0;
    }

    // Constructor with initial string
    public MyStringBuilder(String str) {
        value = new char[str.length() + 16];
        append(str);
    }

    // Ensures internal array has enough capacity
    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > value.length) {
            int newCapacity = Math.max(value.length * 2, minimumCapacity);//this is line that updates the capacity
            char[] newValue = new char[newCapacity];
            System.arraycopy(value, 0, newValue, 0, count);
            value = newValue;
        }
    }

    // Returns current capacity
    public int capacity() {
        return value.length;
    }

    // Appends a string
    public MyStringBuilder append(String str) {
        if (str == null) str = "null";
        int len = str.length();
        ensureCapacity(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }

    // Inserts a string at the specified offset
    public MyStringBuilder insert(int offset, String str) {
        if (offset < 0 || offset > count) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (str == null) str = "null";
        int len = str.length();
        ensureCapacity(count + len);
        System.arraycopy(value, offset, value, offset + len, count - offset);
        str.getChars(0, len, value, offset);
        count += len;
        return this;
    }

    // Deletes characters between start (inclusive) and end (exclusive)
    public MyStringBuilder delete(int start, int end) {
        if (start < 0 || end > count || start > end) {
            throw new StringIndexOutOfBoundsException();
        }
        System.arraycopy(value, end, value, start, count - end);
        count -= (end - start);
        return this;
    }

    // Replaces characters between start and end with the given string
    public MyStringBuilder replace(int start, int end, String str) {
        if (start < 0 || end > count || start > end) {
            throw new StringIndexOutOfBoundsException();
        }
        delete(start, end);
        insert(start, str);
        return this;
    }

    // Reverses the entire character array
    public MyStringBuilder reverse() {
        for (int i = 0, j = count - 1; i < j; i++, j--) {
            char temp = value[i];
            value[i] = value[j];
            value[j] = temp;
        }
        return this;
    }

    // Gets character at a specified index
    public char charAt(int index) {
        if (index < 0 || index >= count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        return value[index];
    }

    // Sets a character at a specified index
    public void setCharAt(int index, char ch) {
        if (index < 0 || index >= count) {
            throw new StringIndexOutOfBoundsException(index);
        }
        value[index] = ch;
    }

    // Converts to string
    @Override
    public String toString() {
        return new String(value, 0, count);
    }

    // Returns the current length of characters
    public int length() {
        return count;
    }

    public static void main(String[] args) {
        
        MyStringBuilder newString = new MyStringBuilder();
        newString.append("Anurag");
        newString.insert(1, "Vi");
        
        System.out.println(newString);
    }
}
