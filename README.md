
=====================================
     ORDERS MANIPULATION PROGRAM    
=====================================

1) Description:
	It is a program, which allows  manipulate a list of orders, 
	which should be put as program arguments and letting easy retrieve a lot of information
	about given orders with various type of reports.
	The user can put csv and xml files as arguments. Each file should contain at least 1 order. 

 	Each order require 5 necessary elements:
	1.	ClientId – alphanumeric, without spaces, no longer than 6 characters,
	2.	RequestId – numeric, long type
	3.	Name – alphanumeric, with spaces, no longer than 255 characters,
	4.	Quantity – numeric, int type 
	5.	e. Price – numeric floating point double-precision
	
	Program informs what is wrong if some file or specific order in a file has wrong format
	and then loads proper orders to in memory database.
  
	Program allows retrieve order details from database and generate 8 different report about:
	1.The total number of orders.
	2. The total number of orders for the client with specified ID.
	3. The total cost of orders.
	4. The total cost of orders for the client with specified ID.
	5. The list of all orders.
	6. The list of all orders for the client with specified ID.
	7. The average order value.
	8. The average order value for the client with specified ID.

	The user can display the chosen report on the screen or save to csv file, 
	which is created In the same directory, where run.sh and run.bat is located.
	User can generate report until press 0 in the console. This operations close the program.

2) Compilation:
	Simply run compile.sh or compile.bat in main directory of the repository.

3) Run:
	Run file run.sh or run.bat in main directory of the repository.

4) Documentation can be found by running doc/index.html in any browser.
