package Stacks;

import java.util.Arrays; 
import java.util.EmptyStackException;
/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano
    @author Timothy M. Henry
    @version 4.0
*/
public final class ArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
   private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayStack()
	{
      this(DEFAULT_CAPACITY);
 	} // end default constructor
	
	public ArrayStack(int initialCapacity)
	{
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      initialized = true;
	} // end constructor

	public void push(T newEntry)
	{
		checkInitialization();
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	} // end push

	public T peek()
	{
		checkInitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else
         return stack[topIndex];
	} // end peek

	public T pop()
	{
		checkInitialization();
		if (isEmpty())
			throw new EmptyStackException();
		else
		{
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--; 
         return top;
		} // end if
   } // end pop

   public boolean isEmpty()
	{
		return topIndex < 0;
	} // end isEmpty
	
	public void clear()
	{
		checkInitialization();
      
      // Remove references to the objects in the stack,
      // but do not deallocate the array
		while (topIndex > -1)
      {
			stack[topIndex] = null;
         topIndex--;
      } // end while
      //	Assertion: topIndex is -1	
	} // end clear
   
   // Throws an exception if this object is not initialized.
   private void checkInitialization()
   {
      if (!initialized)
         throw new SecurityException ("ArrayStack object is not initialized properly.");
   } // end checkInitialization
   
   // Throws an exception if the client requests a capacity that is too large.
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a stack " +
                                         "whose capacity exceeds " +
                                         "allowed maximum.");
   } // end checkCapacity
    
   // Doubles the size of the array stack if it is full
   // Precondition: checkInitialization has been called.
	private void ensureCapacity()
	{
 	   if (topIndex >= stack.length - 1) // If array is full, double its size
      {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      } // end if
	} // end ensureCapacity
} // end ArrayStack