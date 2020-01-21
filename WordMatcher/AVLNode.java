//Student number jamorran(17699381) Joel Andrew Morran
public class AVLNode<T extends Comparable<T> >
{
   private T data;
   private AVLNode<T> leftChild;
   private AVLNode<T> rightChild;
   private int height;
   private int count = 1;
   private StringBuilder border;

   public AVLNode(T data,int count)
   {
      this.data = data;
      this.count = count;
      leftChild = null;
      rightChild = null;
      height = 0;
      border = new StringBuilder();
   }

   public void setLeftChild(AVLNode<T> leftChild)
   {
      this.leftChild = leftChild;
   }

   public void setRightChild(AVLNode<T> rightChild)
   {
      this.rightChild = rightChild;
   }

   public void setData(T data)
   {
      this.data = data;
   }

   public void setHeight(int height)
   {
      this.height = height;
   }

   public AVLNode<T> getLeftChild()
   {
      return leftChild;
   }

   public AVLNode<T> getRightChild()
   {
      return rightChild;
   }

   public T getData()
   {
      return data;
   }

   public int getHeight()
   {
      return height;
   }

   public int getCount()
   {
      return count;
   }

   public void addCount()
   {
      count ++;
   }

   public StringBuilder getBorder()
   {
      return border;
   }

   public void setBorder(String str)
   {
      this.border.append(str);
   }

   //Fix to remove the , and white space from the end of the array
   public void setBorder2(String str)
   {

      this.border.replace(0, border.length(), str);
   }

   public String toString()
   {
      return data + " " + count + " [" + border.toString() + "]";
   }
}