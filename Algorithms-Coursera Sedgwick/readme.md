https://www.coursera.org/learn/algorithms-part1
This course by Kevin Wayne and Robert Sedgewick, provided FOR FREE BY PRINCETON UNIVERSITY covers the essential information about algorithms and data structures, 
with emphasis on applications and scientific performance analysis of Java implementations. 

Part I covers elementary data structures, sorting, and searching algorithms

Exercises and Lectures:

- Dynamic connectivity problem - Union Find
We illustrate our basic approach to developing and analyzing algorithms by considering the dynamic connectivity problem. We introduce the union−find data type and consider several implementations (quick find, quick union, weighted quick union, and weighted quick union with path compression). Finally, we apply the union−find data type to the percolation problem from physical chemistry.

-Analysis of Algorithms
The basis of our approach for analyzing the performance of algorithms is the scientific method. We begin by performing computational experiments to measure the running times of our programs. We use these measurements to develop hypotheses about performance. Next, we create mathematical models to explain their behavior. Finally, we consider analyzing the memory usage of our Java programs.

-Stacks and Queues
We consider two fundamental data types for storing collections of objects: the stack and the queue. We implement each using either a singly-linked list or a resizing array. We introduce two advanced Java features—generics and iterators—that simplify client code. Finally, we consider various applications of stacks and queues ranging from parsing arithmetic expressions to simulating queueing systems.

-Elementary Sorts
We introduce the sorting problem and Java's Comparable interface. We study two elementary sorting methods (selection sort and insertion sort) and a variation of one of them (shellsort). We also consider two algorithms for uniformly shuffling an array. We conclude with an application of sorting to computing the convex hull via the Graham scan algorithm.

-Mergesort
We study the mergesort algorithm and show that it guarantees to sort any array of n items with at most n lg n compares. We also consider a nonrecursive, bottom-up version. We prove that any compare-based sorting algorithm must make at least n lg n compares in the worst case. We discuss using different orderings for the objects that we are sorting and the related concept of stability.
-Quicksort
We introduce and implement the randomized quicksort algorithm and analyze its performance. We also consider randomized quickselect, a quicksort variant which finds the kth smallest item in linear time. Finally, we consider 3-way quicksort, a variant of quicksort that works especially well in the presence of duplicate keys.

-Priority Queues
We introduce the priority queue data type and an efficient implementation using the binary heap data structure. This implementation also leads to an efficient sorting algorithm known as heapsort. We conclude with an applications of priority queues where we simulate the motion of n particles subject to the laws of elastic collision.

-Elementary Symbol Tables
We define an API for symbol tables (also known as associative arrays, maps, or dictionaries) and describe two elementary implementations using a sorted array (binary search) and an unordered list (sequential search). When the keys are Comparable, we define an extended API that includes the additional methods min, max floor, ceiling, rank, and select. To develop an efficient implementation of this API, we study the binary search tree data structure and analyze its performance.
