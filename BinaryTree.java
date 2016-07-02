import java.util.*;
public class BinaryTree<T> 
{
	private T data;
	private BinaryTree<T> parent;
	private BinaryTree<T> left;
	private BinaryTree<T> right;
	
	
	public static <T> void levelOrder(BinaryTree <T> tree){
		ArrayList<BinaryTree<T>>q= new ArrayList<BinaryTree<T>>();
		if(tree!=null)
			q.add(tree);
		while(!q.isEmpty()){
			BinaryTree<T> temp=q.remove(0);
			if(temp.getLeft()!=null)
				q.add(temp.getLeft());
			if(temp.getRight()!=null)
				q.add(temp.getRight());
			System.out.println(temp.getData()+"\t");
		}
	}
	
	public BinaryTree()
	{
		parent = left = right = null;
		data = null;
	}
	
	
	public void makeRoot(T data)
	{
		if (!isEmpty())
		{
			System.out.println("Can't make root. Already exists");
		}
		else
			this.data = data;
	}
	
	public void setData(T data)
	{
		this.data = data;
	}
	
	public void setLeft(BinaryTree<T> tree)
	{
		left = tree;
	}
	public void setRight(BinaryTree<T> tree)
	{
		right = tree;
	}
	public void setParent(BinaryTree<T> tree)
	{
		parent = tree;
	}
	
	public T getData()
	{
		return data;
	}
	public BinaryTree<T> getParent()
	{
		return parent;
	}
	public BinaryTree<T> getLeft()
	{
		return left;
	}
	public BinaryTree<T> getRight()
	{
		return right;
	}
	
	
	public void attachLeft(BinaryTree<T> tree)
	{
		if (tree==null) return;
		if (left!=null || tree.getParent()!=null){
			System.out.println("Can't attach");
			return;
		}
		tree.setParent(this);
		this.setLeft(tree);
	}
	
	public void attachRight(BinaryTree<T> tree)
	{
		if (tree==null)
			return;
		if (right!=null || tree.getParent()!=null)
		{
			System.out.println("Can't attach");
			return;
		}
	
		tree.setParent(this);
		this.setRight(tree);
	}
	
	public BinaryTree<T> detachLeft()
	{
		if (this.isEmpty())
			return null;
		BinaryTree<T> retLeft = left;
		left = null;
		if (retLeft!=null)
			retLeft.setParent(null);
		return retLeft;
	}
	public BinaryTree<T> detachRight()
	{
		if (this.isEmpty()) 
			return null;
		BinaryTree<T> retRight = right;
		right =null;
		if (retRight!=null) 
			retRight.setParent(null);
		return retRight;
	}
	
	public boolean isEmpty()
	{
		if (data == null)
			return true;
		return false;
	}
	
	public void clear()
	{
		left = right = parent =null;
		data = null;
	}
	
	public BinaryTree<T> root()
	{
		if (parent == null)
			return this;
		
		BinaryTree<T> next = parent;
		while (next.getParent()!=null)
			next = next.getParent();
		return next;
		
	}
	
	public static <T> void preorder(BinaryTree<T> t)
	{
		if (t!=null)
		{
			System.out.print(t.getData()+"\t");
			preorder(t.getLeft());	
			preorder(t.getRight());
		}
	}
	
	public static <T> void inorder(BinaryTree<T> t)
	{
		if (t!=null)
		{
			inorder(t.getLeft());
			System.out.print(t.getData() + "\t");
			inorder(t.getRight());
		}
	}
	
	public static <T> void postorder(BinaryTree<T> t)
	{
		if (t!=null)
		{
			postorder(t.getLeft());
			postorder(t.getRight());
			System.out.print(t.getData() + "\t");
		}
	}
	
}