//Student number jamorran(17699381) Joel Andrew Morran
import java.io.PrintWriter;
import java.util.*;

public class AVLTree<T extends Comparable<T> >
{
   private AVLNode<T> root;

   public AVLTree()
   {
      root = null;   
   }

   public void displayElements(PrintWriter p)
   {
      displaySubtreeInOrder(root, p); 
   }

   private void displaySubtreePrefixOrder(AVLNode<T> localRoot, PrintWriter p)
   {
      if (localRoot != null)
      {
         p.println(localRoot.getData());
         displaySubtreePrefixOrder(localRoot.getLeftChild(), p);        
         displaySubtreePrefixOrder(localRoot.getRightChild(), p);
      }
   }

   public void fixBorder()
   {
      fixBorder(root);
   }

   private void fixBorder(AVLNode<T> localRoot)
   {
      if (localRoot != null)
      {
         fixBorder(localRoot.getLeftChild());		
         String str = localRoot.getBorder().toString();		
         if(str.length() > 0)
         {
            String str2 = str.substring(0, str.length() - 2);
            str = str2;
         }

         localRoot.setBorder2(str);		 
         fixBorder(localRoot.getRightChild());
      }
   }

   public void sortBorder()
   {
      sortBorder(root);
   }
   private void sortBorder(AVLNode<T> localRoot)
   {
      if (localRoot != null)
      {
         sortBorder(localRoot.getLeftChild());

         String str = localRoot.getBorder().toString();
         if(str.length() > 0)
         {

            String[] wordArr3 = str.toString().split(",\\s");		  
            //Initialize the ArrayList
            List<String> l2 = new ArrayList<>();  
            //Convert the spilt words Array to an ArrayList for sorting
            l2 =  Arrays.asList(wordArr3);		 		 
            //Sort the ArrayList using selection sort
            selectionSort(l2);		

            String[] wordArray = l2.toArray(new String[l2.size()]);
            //Create a word Array
            String ss = l2.toString();
            String str2 = ss.substring(1, ss.length() - 1);
            ss = str2;

            localRoot.setBorder2(ss);	
         }

         sortBorder(localRoot.getRightChild());
      }
   }

   //Sort method
   public static<E extends Comparable<E>> void swap(List<E> list, int i, int j)
   {
      E temp = list.get(i);
      list.set(i, list.get(j));
      list.set(j, temp);
   }

   //Sort method
   public static <E extends Comparable<E>> void selectionSort(List<E> list)
   {
      int left = 0;
      int right = list.size() - 1;
      for(int i = right; i >= left + 1; i--)
      {
         int mi = getIndexOfMaximum(list, left, i);
         swap(list, mi, i);
      }
   }

   //Sort method
   public static <E extends Comparable<E>> int getIndexOfMaximum(List<E> list, int low, int high)
   {
      int maxIndex = low; 
      for(int i = low + 1; i <= high; i++)
      {
         if (list.get(i).compareTo(list.get(maxIndex)) > 0)
         {
            maxIndex = i;
         }
      }
      return maxIndex;
   }

   public boolean insertElement(T data)
   {
      root = insertElement(root, data);
      return true;
   }

   private int height(AVLNode<T> localRoot)
   {
      if (localRoot == null) 
      {
         return -1;
      }
      else
      {
         return localRoot.getHeight();
      }
   }

   private AVLNode<T> insertElement(AVLNode<T> localRoot, T data)
   {
      if (localRoot == null)
      {
         localRoot = new AVLNode<T>(data, 1);
      }
      else if(data.compareTo(localRoot.getData()) == 0)
      {
         localRoot.addCount();
      }
      else if (data.compareTo(localRoot.getData()) < 0)
      {
         AVLNode<T> leftChild = localRoot.getLeftChild();
         AVLNode<T> subtree = insertElement(leftChild, data);
         localRoot.setLeftChild(subtree);
         localRoot = rebalance(localRoot);
      }
      else 
      {
         AVLNode<T> rightChild = localRoot.getRightChild();
         AVLNode<T> subtree = insertElement(rightChild, data);
         localRoot.setRightChild(subtree);
         localRoot = rebalance(localRoot);
      }

      setHeight(localRoot);
      return localRoot;
   }

   private void setHeight(AVLNode<T> localRoot)
   {
      if (height(localRoot.getLeftChild()) > height(localRoot.getRightChild()))
      {
         localRoot.setHeight(height(localRoot.getLeftChild()) + 1);
      }
      else
      {
         localRoot.setHeight(height(localRoot.getRightChild()) + 1);
      }
   }

   public boolean removeElement(T data)
   {
      // not implemented
      return false;
   }

   public T contains(T searchElement)
   {
      boolean found = false;
      AVLNode<T> current = root;
      T temp = null;

      while (current != null && !found)
      {
         if (current.getData().compareTo(searchElement) > 0)
         {
            current = current.getLeftChild();
         }
         else if (current.getData().compareTo(searchElement) < 0)
         {
            current = current.getRightChild();
         }
         else
         {
            temp = current.getData();
            found = true;
         }
      }
      return temp;
   }

   private AVLNode<T> rightRotation(AVLNode<T> node)
   {
      AVLNode<T> tempNode = node.getLeftChild();
      node.setLeftChild(tempNode.getRightChild());
      tempNode.setRightChild(node);
      setHeight(node);
      return tempNode;
   }

   private AVLNode<T> leftRotation(AVLNode<T> node)
   {
      AVLNode<T> tempNode = node.getRightChild();
      node.setRightChild(tempNode.getLeftChild());
      tempNode.setLeftChild(node);
      setHeight(node);
      return tempNode;
   }

   private AVLNode<T> rightLeftRotation(AVLNode<T> node)
   {
      AVLNode<T> tempNode = node.getRightChild();
      node.setRightChild(rightRotation(tempNode));
      return leftRotation(node);
   }

   private AVLNode<T> leftRightRotation(AVLNode<T> node)
   {
      AVLNode<T> tempNode = node.getLeftChild();
      node.setLeftChild(leftRotation(tempNode));
      return rightRotation(node);
   }

   private int getHeightDifference(AVLNode<T> node)
   {
      int leftHeight = -1;
      int rightHeight = -1;

      if(node.getLeftChild() != null)
      {
         leftHeight = node.getLeftChild().getHeight();
      }

      if(node.getRightChild() != null)
      {
         rightHeight = node.getRightChild().getHeight();
      }

      return leftHeight - rightHeight;
   }

   private AVLNode<T> rebalance(AVLNode<T> rootNode)
   {
      int diff = getHeightDifference(rootNode);

      if(diff < -1)
      {
         if(getHeightDifference(rootNode.getRightChild()) < 0)
         {
            rootNode = leftRotation(rootNode);
         }
         else
         {
            rootNode = rightLeftRotation(rootNode);
         }
      }
      else if(diff > 1)
      {
         if(getHeightDifference(rootNode.getLeftChild()) > 0)
         {
            rootNode = rightRotation(rootNode);
         }
         else
         {
            rootNode = leftRightRotation(rootNode);
         }
      }
      return rootNode;
   }

   private void displaySubtreeInOrder(AVLNode<T> localRoot, PrintWriter p)
   {	
      AVLNode<T> left;
      AVLNode<T> right;
      if(localRoot != null)
      {
         left = localRoot.getLeftChild();	
         displaySubtreeInOrder(left, p);
         p.println(localRoot);
         right = localRoot.getRightChild();
         displaySubtreeInOrder(right, p);			
      }
   }


   public void findingBorders(T data)
   {
      isBorder(root, data);
   }

   private void isBorder(AVLNode<T> localRoot, T data)
   {
      AVLNode<T> left;
      AVLNode<T> right;
      if(localRoot != null)
      {
         //left start
         left = localRoot.getLeftChild();
         isBorder(left, data);
         //Root start
         Object o = localRoot.getData();
         String rootString = o.toString();			
         String dataString = data.toString();	

         int same = 0;
         StringBuilder sb = new StringBuilder();
         StringBuilder found = new StringBuilder();

         if(rootString.length() == dataString.length())
         {
            for(int i = 0; i < rootString.length(); i++)
            {
               if(rootString.charAt(i) == dataString.charAt(i))
               {
                  same ++;
               }
            }
            if(same == rootString.length() - 1)
            {

               found = localRoot.getBorder();
               String[] split = found.toString().split(",");
               boolean ch = false;
               for(String a: split)
               {
                  if(a.equals(dataString))
                  {
                     ch = true;         
                  }
               }
               if(ch == false)
               {                   
                  sb.append(dataString + ", ");							
                  String str = sb.toString();
                  localRoot.setBorder(str);															
               }							
            }
         }			
         //Right start
         right = localRoot.getRightChild();
         isBorder(right, data);
      }
   }
}