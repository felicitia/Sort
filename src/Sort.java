import java.util.Random;
import java.util.Scanner;


public class Sort {

	public static int[] a;
	public static int[] b;//辅助数组
	public static final int n = 10;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initArray();
//		for(int x: a)
//		{
//			System.out.println(x);
//		}
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		switch(choice)
		{
		case 1:
			selectSort(a, n);
			break;
		case 2:
			halfInsertSort(a, n);
			break;
		case 3:
			insertionSort(a, n);
			break;
		case 4:
			bubbleSort(a, n);
			break;
		case 5:
			mergeSort(a, 0, n-1);
			break;
		case 6:
			quickSort(a, 0, n-1);
			break;
		case 7:
			heapSort(a, n-1);
			break;
		}
		for(int x: a)
		{
			System.out.println(x);
		}
	}
	
	public static void initArray()
	{
		a = new int[n];
		b = new int[n];
		Random random = new Random();
		for(int i=0; i<n; i++)
		{
			a[i] = random.nextInt();
		}
//		a[0] = 3;
//		a[3] = 3;
	}

	public static void swap(int[] a, int m, int n)
	{
		int tmp = a[m];
		a[m] = a[n];
		a[n] = tmp;
	}
	/**
	 * 基于比较的排序
	 * @param a 要排序的数组
	 * @param n 数组的大小
	 */
	public static void selectSort(int[] a, int n)
	{
		for(int i=0; i<n; i++)
		{
			int j=i;
			for(int k=j+1; k<n; k++)
			{
				if(a[k] < a[j])
				{
					j = k;
				}
			}
			swap(a, i, j);
		}
	}
	public static void halfInsertSort(int[] a, int n)
	{
		int low, high, mid;
		int key;
		for(int i=1; i<n; i++)
		{
			key = a[i];
			low = 0; 
			high = i-1;
			while(low<=high)
			{
				mid = (low+high)/2;
				if(key > a[mid])
				{
					low = mid + 1;
				}
				else if(key < a[mid])
				{
					high = mid - 1;
				}
				else
				{
					high = mid;
					break;
				}
			}
			for(int j=i-1; j>=high+1; j--)
			{
				a[j+1] = a[j];
			}
			a[high+1] = key;
		}
	}
	
	public static void insertionSort(int[] a, int n)
	{
		int key;
		int j;
		for(int i=1; i<n; i++)
		{
			key = a[i];
			j = i-1;
			while(j>=0 && key<a[j])
			{
				a[j+1] = a[j];
				j--;
			}
			a[++j] = key;
		}
	}
	public static void bubbleSort(int[] a, int n)
	{
		boolean exchange;
		for(int i=1; i<n; i++)
		{
			exchange = false;
			for(int j=0; j<n-i; j++)
			{
				if(a[j] > a[j+1])
				{
					swap(a, j, j+1);
					exchange = true;
				}
			}
			if(!exchange)
			{
				break;
			}
		}
	}
	/**
	 * 分治思想的排序，复杂度nlgn
	 * @param a 需要排序的数组
	 * @param i 需要排序的起始位置
	 * @param j 需要排序的终止位置
	 */
	public static void mergeSort(int[] a, int i, int j)
	{
		if(i<j)
		{
			int k = (i+j)/2;
			mergeSort(a, i, k);
			mergeSort(a, k+1, j);
			int low = i;
			int high = k+1;
			int t = i;
			while(low <=k && high<=j)
			{
				if(a[low] < a[high])
				{
					b[t++] = a[low++];
				}
				else
				{
					b[t++] = a[high++];
				}
			}
			if(low <= k)  //不加等号b有的位置没有填上
			{
				for(int x=low; x<=k; x++)
				{
					b[t++] = a[x];
				}
			}
			else if(high <= j)
			{
				for(int x=high; x<=j; x++)
				{
					b[t++] = a[x];
				}
			}
			for(int x=i; x<=j; x++)
			{
				a[x] = b[x];
			}
		}
	}
	
	public static void quickSort(int[] a, int i, int j)
	{
		if(i < j)
		{
			int k = partition(a, i, j, a[getPos(i, j)]);
			quickSort(a, i, k-1);
			quickSort(a, k, j);
		}
	}
	public static int getPos(int i, int j)
	{
		int pos; 
		if(i == j)
		{
			pos = i;
		}
		else
		{
			Random random = new Random();
			pos = i + random.nextInt(j-i)+1;
		}
		return pos;
	}
	public static int partition(int[] a, int i, int j, int pivot)
	{
		int left = i;
		int right = j;
		do
		{
			swap(a, left, right);
			while(a[left]<pivot && left<=j)
			{
				left++;
			}
			while(a[right] >= pivot && right>=i)
			{
				right--;
			}
		}while(left<=right);
		return left;
	} 
	
	public static void arrangeHeap(int[] a, int first, int last)
	{
		int r = first;
		while(r <= last/2)
		{
			if(r==last/2 && last%2==0)//r有一个儿子，在2r，则左儿子不可能有儿子，无需整理
			{
				swap(a, r, 2*r);
				r = last;//结束
			}
			//跟小的换
			else if(a[2*r] <= a[2*r+1] && a[r] > a[2*r])
			{
				swap(a, r, 2*r);//和左儿子换，再整理左儿子
				r = 2*r;
			}
			else if(a[2*r] > a[2*r+1] && a[r] > a[2*r+1])
			{
				swap(a, r, 2*r+1);//和右儿子换，再整理右儿子
				r = 2*r+1;
			}
			else
			{
				r = last;//结束
			}
		}
	}
	
	
	/**
	 *堆排序数组下标要从1开始，若为0，乘以2还是0，不是孩子的下标,
	 *为了不更改程序，则堆排序会少排序下标为0的那个数，比其他排序的
	 *大小少1，另外为了不用辅助数组，排完为倒序 
	 * @param a
	 * @param n
	 */
	public static void heapSort(int[] a, int n)
	{
		for(int i=n/2; i>=1; i--)
		{
			arrangeHeap(a, i, n);
		}
		for(int i=n; i>=2; i--)
		{
			swap(a, 1, i);
			arrangeHeap(a, 1, i-1);
		}
	}
}
