Last Updated:March 20, 2017
--------------------------------------------------------------

The SortTree folder holds the sorted XML files as well as sortTree.java class implementation source code and JUnit Tester.java 
class. 

---------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------

SortTree Class
---------------------------------------------------------------------------------------------

This program sorts an XML tree using a recursive method. It has three options to sort the XML tree.
These are 1 for Descending order, 2 for CaseSensitive Order and 3 for sorting by the value of Numbers (HandleNumbers)

The class contains a public method and three private methods. The private methods sort the paragraph list, 
the section list and the Hash map. The third method is called in all the methods inorder to sort a hash map by it's value. 
This method provides options for the differnet cases of ordering the XML tree.

JUnit tester
-------------------------------------------------

The tester.java contains four methods to test each case. 


Default:
- the default operation is in ascending order.

Limitation:
- The sort method is recursive so the stack has a limited depth.
